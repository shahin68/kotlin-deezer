/*
 * Copyright (c) 2020. By Shahin Montazeri (shahin.montazeri@gmail.com) 
 * Programmed for demonstration purposes
 */

package com.shahin.deezer.data.sources.albums.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.shahin.deezer.data.models.album.Album
import com.shahin.deezer.data.models.album.AlbumShell
import com.shahin.deezer.data.services.AlbumsApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Albums Remote Source providing access to
 * @see AlbumsPagingDataSource
 */
class AlbumsRemoteSourceImpl @Inject constructor(
    private val albumsApi: AlbumsApi
): AlbumsRemoteSource {

    override fun fetchAlbums(artistId: String): Flow<PagingData<AlbumShell>> {
        return Pager(
            config = PagingConfig(
                pageSize = 25,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                AlbumsPagingDataSource(
                    albumsApi,
                    artistId
                )
            }
        ).flow
    }

}