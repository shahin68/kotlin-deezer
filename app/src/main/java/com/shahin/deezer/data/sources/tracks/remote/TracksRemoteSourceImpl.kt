/*
 * Copyright (c) 2020. By Shahin Montazeri (shahin.montazeri@gmail.com) 
 * Programmed for demonstration purposes
 */

package com.shahin.deezer.data.sources.tracks.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.shahin.deezer.data.models.ResponseData
import com.shahin.deezer.data.models.tracks.Track
import com.shahin.deezer.data.services.ServiceType
import com.shahin.deezer.commons.paging.sources.PagingDataSource
import com.shahin.deezer.commons.paging.sources.PagingRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Tracks Remote Source providing access to
 * @see TracksPagingDataSource
 */
class TracksRemoteSourceImpl @Inject constructor(
    private val pagingDataSource: PagingDataSource<Track>
): TracksRemoteSource {
    override fun fetchTracks(albumId: String): Flow<PagingData<ResponseData<Track>>> {
        return Pager(
            config = PagingConfig(
                pageSize = 25,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                PagingRemoteDataSource<Track>(
                    albumId,
                    ServiceType.Tracks,
                    pagingDataSource
                )
            }
        ).flow.map {
            it.insertHeaderItem(
                ResponseData(albumId, Track())
            )
        }
    }
}