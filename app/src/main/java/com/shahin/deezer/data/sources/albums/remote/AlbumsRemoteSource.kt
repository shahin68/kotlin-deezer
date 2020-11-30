package com.shahin.deezer.data.sources.albums.remote

import androidx.paging.PagingData
import com.shahin.deezer.data.models.album.Album
import kotlinx.coroutines.flow.Flow

interface AlbumsRemoteSource {
    fun fetchAlbums(artistId: String?): Flow<PagingData<Album>>
}