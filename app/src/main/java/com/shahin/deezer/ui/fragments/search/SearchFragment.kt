/*
 * Copyright (c) 2020. By Shahin Montazeri (shahin.montazeri@gmail.com)
 * Programmed for demonstration purposes
 */

package com.shahin.deezer.ui.fragments.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadState
import androidx.paging.map
import androidx.recyclerview.widget.RecyclerView
import com.asan.amvlet.chat.ui.widget.StickyItemDecoration
import com.shahin.deezer.R
import com.shahin.deezer.commons.MyLoadStateAdapter
import com.shahin.deezer.data.models.artist.Artist
import com.shahin.deezer.databinding.FragmentSearchBinding
import com.shahin.deezer.extensions.hideKeyboard
import com.shahin.deezer.extensions.textChanges
import com.shahin.deezer.ui.fragments.BaseFragment
import com.shahin.deezer.ui.fragments.search.adapter.ArtistsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {

    private val viewModel: SearchViewModel by viewModels()

    private var searchJob: Job? = null
    private val searchAdapter = ArtistsAdapter {
        navigateToAlbums(it)
    }
    private val offsetScrollThreshold = 3000

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupList()
        handleSearch()

        binding.fab.setOnClickListener {
            binding.recyclerView.smoothScrollToPosition(0)
        }
    }

    private fun setupList() {
        binding.recyclerView.apply {
            adapter = searchAdapter.withLoadStateFooter(
                footer = MyLoadStateAdapter()
            )
            addItemDecoration(StickyItemDecoration(
                parent = this,
                shouldFadeOutHeader = false,
                isHeader = { position ->
                    searchAdapter.getItemViewType(position) == ArtistsAdapter.ViewTypes.Header.ordinal
                }
            ))
        }

        binding.recyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if(binding.recyclerView.computeVerticalScrollOffset() < offsetScrollThreshold){
                    binding.fab.hide()
                } else{
                    binding.fab.show()
                }
            }
        })

        searchAdapter.loadStateFlow.onEach { state ->
            if (isAdded) {
                binding.loading.isVisible = state.refresh is LoadState.Loading
            }
        }.launchIn(lifecycleScope)
    }

    @OptIn(ExperimentalPagingApi::class)
    private fun handleSearch() {
        binding.searchView
            .textChanges()
            .filterNotNull()
            .onEach {
                binding.loading.isVisible = it.isNotEmpty()
            }
            .debounce(500)
            .filter { it.isNotEmpty() }
            .onEach {
                searchJob?.cancel()
                searchJob = lifecycleScope.launch {
                    viewModel.search(it.trim()).collectLatest {
                        if (isAdded) {
                            binding.loading.isVisible = false
                            searchAdapter.submitData(it)
                        }
                    }
                }
            }
            .launchIn(lifecycleScope)
    }

    private fun navigateToAlbums(artist: Artist) {
        findNavController().navigate(
            SearchFragmentDirections.actionFragmentSearchToFragmentAlbums(
                artistId = artist.id,
                artistName = artist.name
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        requireActivity().hideKeyboard()
    }
}