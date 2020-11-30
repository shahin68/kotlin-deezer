package com.shahin.deezer.data.sources.tracks

import androidx.paging.PagingData
import com.shahin.deezer.data.models.tracks.Track
import com.shahin.deezer.data.sources.tracks.local.TracksLocalSource
import com.shahin.deezer.data.sources.tracks.remote.TracksRemoteSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TracksRepositoryImpl @Inject constructor(
    private val tracksLocalSource: TracksLocalSource,
    private val tracksRemoteSource: TracksRemoteSource
): TracksRepository {
    override fun fetchTracks(albumId: String?): Flow<PagingData<Track>> {
        return tracksRemoteSource.fetchTracks(
            albumId = albumId
        )
    }
}