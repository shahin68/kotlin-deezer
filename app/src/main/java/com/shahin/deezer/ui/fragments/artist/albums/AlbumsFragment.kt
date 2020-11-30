package com.shahin.deezer.ui.fragments.artist.albums

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.shahin.deezer.R
import com.shahin.deezer.data.models.album.Album
import com.shahin.deezer.data.models.artist.Artist
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

        val items = arrayListOf<Album>()
        /*for (i in 0..20) {
            items.add(
                Album(
                    id = i.toString(),
                    title = "Album Name $i",
                    artist = Artist(
                        name = "Artist Name $i"
                    )
                )
            )
        }*/
        setupRecycler(items)
    }

    private fun setupRecycler(list: List<Album>) {
        binding.recyclerView.adapter = AlbumsAdapter(list) {
            navigateToTracks(it)
        }
    }

    private fun navigateToTracks(albumModel: Album) {
        findNavController().navigate(
            AlbumsFragmentDirections.actionFragmentAlbumsToFragmentTracks(
                albumName = albumModel.title,
                artistName = albumModel.artist.name
            )
        )
    }
}