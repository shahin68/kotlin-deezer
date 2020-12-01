/*
 * Copyright (c) 2020. By Shahin Montazeri (shahin.montazeri@gmail.com
 */

package com.shahin.deezer.data.sources.albums.remote

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.shahin.deezer.data.models.album.Album
import com.shahin.deezer.data.services.AlbumsApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AlbumsRemoteSourceImpl @Inject constructor(
    private val albumsApi: AlbumsApi
): AlbumsRemoteSource {

    override fun fetchAlbums(artistId: String): Flow<PagingData<Album>> {
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