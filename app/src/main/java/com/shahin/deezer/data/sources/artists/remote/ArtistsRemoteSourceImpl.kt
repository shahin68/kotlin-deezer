/*
 * Copyright (c) 2020. By Shahin Montazeri (shahin.montazeri@gmail.com) 
 * Programmed for demonstration purposes
 */

package com.shahin.deezer.data.sources.artists.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.shahin.deezer.data.models.ResponseData
import com.shahin.deezer.data.models.artist.Artist
import com.shahin.deezer.data.services.ServiceType
import com.shahin.deezer.commons.paging.sources.PagingDataSource
import com.shahin.deezer.commons.paging.sources.PagingRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Artists Remote Source providing access to
 * @see ArtistsPagingDataSource
 */
class ArtistsRemoteSourceImpl @Inject constructor(
    private val pagingDataSource: PagingDataSource<Artist>
) : ArtistsRemoteSource {

    override fun search(artistName: String): Flow<PagingData<ResponseData<Artist>>> {
        return Pager(
            config = PagingConfig(
                pageSize = 25,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                PagingRemoteDataSource<Artist>(
                    artistName,
                    ServiceType.Artists,
                    pagingDataSource
                )
            }
        ).flow.map {
            it.insertHeaderItem(
                ResponseData(artistName, Artist())
            )
        }
    }
}