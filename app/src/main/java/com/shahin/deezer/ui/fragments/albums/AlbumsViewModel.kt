package com.shahin.deezer.ui.fragments.albums

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.shahin.deezer.data.models.album.Album
import com.shahin.deezer.data.sources.albums.AlbumsRepository
import kotlinx.coroutines.flow.Flow

class AlbumsViewModel @ViewModelInject constructor(
    private val albumsRepository: AlbumsRepository
) : ViewModel() {

    fun fetchAlbums(artistId: String?): Flow<PagingData<Album>> {
        return albumsRepository.fetchAlbums(artistId)
            .cachedIn(viewModelScope)
    }

}