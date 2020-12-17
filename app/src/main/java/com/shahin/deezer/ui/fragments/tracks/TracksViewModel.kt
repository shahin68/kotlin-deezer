/*
 * Copyright (c) 2020. By Shahin Montazeri (shahin.montazeri@gmail.com) 
 * Programmed for demonstration purposes
 */

package com.shahin.deezer.ui.fragments.tracks

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.shahin.deezer.data.models.ResponseData
import com.shahin.deezer.data.models.tracks.Track
import com.shahin.deezer.data.sources.tracks.TracksRepository
import kotlinx.coroutines.flow.Flow

class TracksViewModel @ViewModelInject constructor(
    private val tracksRepository: TracksRepository
) : ViewModel() {

    private var currentQueryValue: String? = null

    /**
     * keep Tracks alive in viewmodel until it's changed
     */
    private var currentSearchResult: Flow<PagingData<ResponseData<Track>>>? = null

    fun fetchTracks(albumId: String): Flow<PagingData<ResponseData<Track>>> {
        val lastResult = currentSearchResult
        if (albumId == currentQueryValue && lastResult != null) {
            return lastResult
        }
        currentQueryValue = albumId
        val newResult: Flow<PagingData<ResponseData<Track>>> = tracksRepository.fetchTracks(albumId = albumId)
            .cachedIn(viewModelScope)
        currentSearchResult = newResult
        return newResult
    }

}