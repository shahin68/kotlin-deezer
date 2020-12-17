/*
 * Copyright (c) 2020. By Shahin Montazeri (shahin.montazeri@gmail.com) 
 * Programmed for demonstration purposes
 */

package com.shahin.deezer.data.sources.albums

import androidx.paging.PagingData
import com.shahin.deezer.data.models.ResponseData
import com.shahin.deezer.data.models.album.Album
import com.shahin.deezer.data.sources.albums.local.AlbumsLocalSource
import com.shahin.deezer.data.sources.albums.remote.AlbumsRemoteSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Albums Main Repository
 * Providing access to All Data regarding Albums
 */
class AlbumsRepositoryImpl @Inject constructor(
    private val albumsLocalSource: AlbumsLocalSource,
    private val albumsRemoteSource: AlbumsRemoteSource
): AlbumsRepository {
    override fun fetchAlbums(artistId: String): Flow<PagingData<ResponseData<Album>>> {
        return albumsRemoteSource.fetchAlbums(artistId)
    }

}