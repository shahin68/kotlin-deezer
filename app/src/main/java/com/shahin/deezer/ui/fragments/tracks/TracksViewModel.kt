package com.shahin.deezer.ui.fragments.tracks

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.shahin.deezer.data.models.tracks.Track
import com.shahin.deezer.data.sources.tracks.TracksRepository
import kotlinx.coroutines.flow.Flow

class TracksViewModel @ViewModelInject constructor(
    private val tracksRepository: TracksRepository
) : ViewModel() {

    fun fetchTracks(albumId: String?): Flow<PagingData<Track>> {
        return tracksRepository.fetchTracks(albumId = albumId)
            .cachedIn(viewModelScope)
    }
}