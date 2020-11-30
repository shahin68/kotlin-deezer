package com.shahin.deezer.ui.fragments.artist.albums

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
import com.shahin.deezer.R
import com.shahin.deezer.data.models.album.Album
import com.shahin.deezer.databinding.FragmentAlbumsBinding
import com.shahin.deezer.ui.fragments.BaseFragment
import com.shahin.deezer.ui.fragments.artist.albums.adapter.AlbumsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AlbumsFragment : BaseFragment<FragmentAlbumsBinding>(R.layout.fragment_albums) {

    private val viewModel: AlbumsViewModel by viewModels()
    private val args: AlbumsFragmentArgs by navArgs()
    private lateinit var albumsAdapter: AlbumsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAlbumsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)


        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        setupList()
        fetchAlbums()
    }

    private fun setupList() {
        albumsAdapter = AlbumsAdapter(args.artistName) {
            navigateToTracks(it)
        }
        binding.recyclerView.adapter = albumsAdapter

        albumsAdapter.loadStateFlow.onEach { state ->
            if (isAdded) {
                binding.loading.isVisible = state.refresh is LoadState.Loading
                if (albumsAdapter.itemCount == 0) {
                    binding.messageTv.isVisible = true
                    when (state.refresh) {
                        is LoadState.NotLoading -> {
                            binding.messageTv.text = getString(R.string.generic_text_found_nothing)
                        }
                        is LoadState.Loading -> {
                            binding.messageTv.text = getString(R.string.generic_text_loading)
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

    private fun fetchAlbums() {
        lifecycleScope.launch {
            viewModel.fetchAlbums(args.artistId).collectLatest {
                if (isAdded) {
                    binding.loading.isVisible = false
                    binding.messageTv.isVisible = false
                    if (::albumsAdapter.isInitialized) {
                        albumsAdapter.submitData(it)
                    }
                }
            }
        }
    }

    private fun navigateToTracks(albumModel: Album) {
        findNavController().navigate(
            AlbumsFragmentDirections.actionFragmentAlbumsToFragmentTracks(
                albumName = albumModel.title,
                artistName = albumModel.artist?.name ?: args.artistName,
                cover = albumModel.coverMedium
            )
        )
    }
}