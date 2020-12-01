/*
 * Copyright (c) 2020. By Shahin Montazeri (shahin.montazeri@gmail.com
 */

package com.shahin.deezer.data.sources.tracks.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.shahin.deezer.data.models.tracks.Track
import com.shahin.deezer.data.models.tracks.TrackShell
import com.shahin.deezer.data.services.TracksApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Tracks Remote Source providing access to
 * @see TracksPagingDataSource
 */
class TracksRemoteSourceImpl @Inject constructor(
    private val tracksApi: TracksApi
): TracksRemoteSource {

    override fun fetchTracks(albumId: String): Flow<PagingData<TrackShell>> {
        return Pager(
            config = PagingConfig(
                pageSize = 25,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                TracksPagingDataSource(
                    tracksApi,
                    albumId
                )
            }
        ).flow.map {
            it.insertHeaderItem(
                TrackShell(Track())
            )
        }
    }

}