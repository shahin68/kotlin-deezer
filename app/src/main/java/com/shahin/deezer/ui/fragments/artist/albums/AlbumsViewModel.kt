package com.shahin.deezer.ui.fragments.artist.albums

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.shahin.deezer.data.sources.artists.ArtistsRepository

class AlbumsViewModel @ViewModelInject constructor(
    private val artistsRepository: ArtistsRepository
) : ViewModel() {

    fun fetchAlbums() {

    }

}