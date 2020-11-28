package com.shahin.deezer.ui.fragments.artist.tracks

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.shahin.deezer.data.sources.artists.ArtistsRepository

class TracksViewModel @ViewModelInject constructor(
    private val artistsRepository: ArtistsRepository
) : ViewModel() {

    fun fetchTracks() {

    }

}