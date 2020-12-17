/*
 * Copyright (c) 2020. By Shahin Montazeri (shahin.montazeri@gmail.com) 
 * Programmed for demonstration purposes
 */

package com.shahin.deezer.data.sources.albums.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.shahin.deezer.data.models.ResponseData
import com.shahin.deezer.data.models.album.Album
import com.shahin.deezer.data.services.ServiceType
import com.shahin.deezer.commons.paging.sources.PagingDataSource
import com.shahin.deezer.commons.paging.sources.PagingRemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Albums Remote Source providing access to
 * @see AlbumsPagingDataSource
 */
class AlbumsRemoteSourceImpl @Inject constructor(
    private val pagingDataSource: PagingDataSource<Album>
): AlbumsRemoteSource {

    override fun fetchAlbums(artistId: String): Flow<PagingData<ResponseData<Album>>> {
        return Pager(
            config = PagingConfig(
                pageSize = 25,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                PagingRemoteDataSource<Album>(
                    artistId,
                    ServiceType.Albums,
                    pagingDataSource
                )
            }
        ).flow
    }


}