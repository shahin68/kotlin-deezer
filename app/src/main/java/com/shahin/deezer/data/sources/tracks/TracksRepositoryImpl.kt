/*
 * Copyright (c) 2020. By Shahin Montazeri (shahin.montazeri@gmail.com) 
 * Programmed for demonstration purposes
 */

package com.shahin.deezer.data.sources.tracks

import androidx.paging.PagingData
import com.shahin.deezer.data.models.ResponseData
import com.shahin.deezer.data.models.tracks.Track
import com.shahin.deezer.data.sources.tracks.local.TracksLocalSource
import com.shahin.deezer.data.sources.tracks.remote.TracksRemoteSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Artists Main Repository
 * Providing access to All Data regarding Artists
 */
class TracksRepositoryImpl @Inject constructor(
    private val tracksLocalSource: TracksLocalSource,
    private val tracksRemoteSource: TracksRemoteSource
): TracksRepository {
    override fun fetchTracks(albumId: String): Flow<PagingData<ResponseData<Track>>> {
        return tracksRemoteSource.fetchTracks(
            albumId = albumId
        )
    }
}