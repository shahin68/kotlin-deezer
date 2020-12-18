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
import com.shahin.deezer.data.services.ServiceType
import com.shahin.deezer.data.sources.remote.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TracksViewModel @ViewModelInject constructor(
    private val repository: Repository
) : ViewModel() {

    private var currentQueryValue: String? = null

    /**
     * keep Tracks alive in viewmodel until it's changed
     */
    private var currentSearchResult: Flow<PagingData<ResponseData>>? = null

    fun fetchTracks(albumId: String): Flow<PagingData<ResponseData>> {
        val lastResult = currentSearchResult
        if (albumId == currentQueryValue && lastResult != null) {
            return lastResult
        }
        currentQueryValue = albumId
        val newResult: Flow<PagingData<ResponseData>> =
            repository.fetch(albumId, ServiceType.Tracks).map {
                it.insertHeaderItem(
                    ResponseData(albumId, Track())
                )
            }.cachedIn(viewModelScope)
        currentSearchResult = newResult
        return newResult
    }

}