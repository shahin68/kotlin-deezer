package com.shahin.deezer.data.sources.albums

import androidx.paging.PagingData
import com.shahin.deezer.data.models.album.Album
import kotlinx.coroutines.flow.Flow

interface AlbumsRepository {
    fun fetchAlbums(
        artistId: String
    ): Flow<PagingData<Album>>
}