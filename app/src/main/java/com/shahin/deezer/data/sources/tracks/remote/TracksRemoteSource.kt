/*
 * Copyright (c) 2020. By Shahin Montazeri (shahin.montazeri@gmail.com) 
 * Programmed for demonstration purposes
 */

package com.shahin.deezer.data.sources.tracks.remote

import androidx.paging.PagingData
import com.shahin.deezer.data.models.tracks.Track
import com.shahin.deezer.data.models.tracks.TrackShell
import kotlinx.coroutines.flow.Flow

interface TracksRemoteSource {
    fun fetchTracks(albumId: String): Flow<PagingData<TrackShell>>
}