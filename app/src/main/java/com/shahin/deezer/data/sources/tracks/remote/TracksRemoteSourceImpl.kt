package com.shahin.deezer.data.sources.tracks.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.shahin.deezer.data.models.tracks.Track
import com.shahin.deezer.data.services.TracksApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TracksRemoteSourceImpl @Inject constructor(
    private val tracksApi: TracksApi
): TracksRemoteSource {
    override fun fetchTracks(albumId: String?): Flow<PagingData<Track>> {
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
        ).flow
    }

}