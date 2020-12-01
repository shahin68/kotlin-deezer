/*
 * Copyright (c) 2020. By Shahin Montazeri (shahin.montazeri@gmail.com
 */

package com.shahin.deezer.ui.fragments.tracks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import com.asan.amvlet.chat.ui.widget.StickyItemDecoration
import com.shahin.deezer.R
import com.shahin.deezer.commons.MyLoadStateAdapter
import com.shahin.deezer.databinding.FragmentTracksBinding
import com.shahin.deezer.extensions.loadImage
import com.shahin.deezer.ui.fragments.BaseFragment
import com.shahin.deezer.ui.fragments.tracks.adapter.TracksAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TracksFragment : BaseFragment<FragmentTracksBinding>(R.layout.fragment_tracks) {

    private val viewModel: TracksViewModel by viewModels()
    private val args: TracksFragmentArgs by navArgs()
    private lateinit var tracksAdapter: TracksAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTracksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)

        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        binding.appBarImage.loadImage(args.cover)
        binding.toolbar.title = args.albumName
        binding.toolbar.subtitle = args.artistName

        setupList()
        fetchTracks()
    }

    private fun setupList() {
        tracksAdapter = TracksAdapter {
            // to do something with the track
        }

        binding.recyclerView.apply {
            adapter = tracksAdapter.withLoadStateFooter(
                footer = MyLoadStateAdapter()
            )
            addItemDecoration(StickyItemDecoration(
                parent = this,
                shouldFadeOutHeader = false,
                isHeader = { position ->
                    tracksAdapter.getItemViewType(position) == TracksAdapter.ViewTypes.Header.ordinal
                }
            ))
        }

        tracksAdapter.loadStateFlow.onEach { state ->
            if (isAdded) {
                binding.loading.isVisible = state.refresh is LoadState.Loading
            }
        }.launchIn(lifecycleScope)
    }

    private fun fetchTracks() {
        lifecycleScope.launch {
            viewModel.fetchTracks(args.albumId ?: "").collectLatest {
                if (isAdded) {
                    binding.loading.isVisible = false
                    if (::tracksAdapter.isInitialized) {
                        tracksAdapter.submitData(it)
                    }
                }
            }
        }
    }
}