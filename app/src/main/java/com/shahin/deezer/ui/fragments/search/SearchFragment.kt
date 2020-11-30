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
import androidx.paging.flatMap
import androidx.paging.map
import androidx.recyclerview.widget.RecyclerView
import com.shahin.deezer.R
import com.shahin.deezer.databinding.FragmentSearchBinding
import com.shahin.deezer.extensions.fastScrollUp
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
        navigateToAlbums()
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

        setupListAdapter()
        handleSearch()

        binding.fab.setOnClickListener {
            binding.recyclerView.fastScrollUp()
        }
    }

    private fun setupListAdapter() {
        binding.recyclerView.adapter = searchAdapter

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
                if (searchAdapter.itemCount == 0) {
                    binding.messageTv.isVisible = true
                    when (state.refresh) {
                        is LoadState.NotLoading -> {
                            if (binding.searchView.query.isEmpty()) {
                                binding.messageTv.text = getString(R.string.search_placeholder_text_start_searching)
                            } else {
                                binding.messageTv.text = getString(R.string.generic_text_found_nothing)
                            }
                        }
                        is LoadState.Loading -> {
                            binding.messageTv.text = getString(R.string.search_placeholder_text_start_searching)
                        }
                        is LoadState.Error -> {
                            binding.messageTv.text = "api error"
                        }
                    }
                } else {
                    binding.messageTv.isVisible = state.refresh is LoadState.Error
                    binding.messageTv.text = "api error"
                }
            }
        }.launchIn(lifecycleScope)
    }

    @OptIn(ExperimentalPagingApi::class)
    private fun handleSearch() {
        binding.searchView
            .textChanges()
            .filterNotNull()
            .onEach {
                if (it.isEmpty() && searchAdapter.itemCount == 0) {
                    binding.messageTv.isVisible = true
                    binding.messageTv.text = getString(R.string.search_placeholder_text_start_searching)
                }
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
                            binding.messageTv.isVisible = false
                            searchAdapter.submitData(it)
                        }
                    }
                }
            }
            .launchIn(lifecycleScope)
    }

    private fun navigateToAlbums() {
        findNavController().navigate(
            SearchFragmentDirections.actionFragmentSearchToFragmentAlbums()
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        requireActivity().hideKeyboard()
    }
}