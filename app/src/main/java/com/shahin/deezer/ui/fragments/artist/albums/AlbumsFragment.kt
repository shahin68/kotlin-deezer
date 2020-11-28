package com.shahin.deezer.ui.fragments.artist.albums

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.shahin.deezer.R
import com.shahin.deezer.data.models.AlbumModel
import com.shahin.deezer.databinding.FragmentAlbumsBinding
import com.shahin.deezer.ui.fragments.BaseFragment
import com.shahin.deezer.ui.fragments.artist.albums.adapter.AlbumsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumsFragment : BaseFragment<FragmentAlbumsBinding>(R.layout.fragment_albums) {

    private val viewModel: AlbumsViewModel by viewModels()

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

        viewModel.fetchAlbums()

        val items = arrayListOf<AlbumModel>()
        for (i in 0..20) {
            items.add(
                AlbumModel(
                    i.toLong(),
                    "Album Name $i",
                    "Artist Name $i",
                    i.toString()
                )
            )
        }
        setupRecycler(items)
    }

    private fun setupRecycler(list: List<AlbumModel>) {
        binding.recyclerView.adapter = AlbumsAdapter(list) {
            navigateToTracks(it)
        }
    }

    private fun navigateToTracks(albumModel: AlbumModel) {
        findNavController().navigate(
            AlbumsFragmentDirections.actionFragmentAlbumsToFragmentTracks(
                albumName = albumModel.albumName,
                artistName = albumModel.albumArtist
            )
        )
    }
}