/*
 * Copyright (c) 2020. By Shahin Montazeri (shahin.montazeri@gmail.com) 
 * Programmed for demonstration purposes
 */

package com.shahin.deezer.ui.fragments.albums

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.shahin.deezer.data.models.album.Album
import com.shahin.deezer.data.models.album.AlbumShell
import com.shahin.deezer.data.sources.albums.AlbumsRepository
import kotlinx.coroutines.flow.Flow

class AlbumsViewModel @ViewModelInject constructor(
    private val albumsRepository: AlbumsRepository
) : ViewModel() {

    private var currentQueryValue: String? = null

    /**
     * keep Albums alive in viewmodel until it's changed
     */
    private var currentSearchResult: Flow<PagingData<AlbumShell>>? = null

    fun fetchAlbums(artistId: String): Flow<PagingData<AlbumShell>> {
        val lastResult = currentSearchResult
        if (artistId == currentQueryValue && lastResult != null) {
            return lastResult
        }
        currentQueryValue = artistId
        val newResult: Flow<PagingData<AlbumShell>> = albumsRepository.fetchAlbums(artistId)
            .cachedIn(viewModelScope)
        currentSearchResult = newResult
        return newResult
    }
}