package com.shahin.deezer.data.sources.tracks.remote

import androidx.paging.PagingData
import com.shahin.deezer.data.models.tracks.Track
import kotlinx.coroutines.flow.Flow

interface TracksRemoteSource {
    fun fetchTracks(albumId: String?): Flow<PagingData<Track>>
}