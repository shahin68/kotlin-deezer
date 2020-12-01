package com.shahin.deezer.data.sources.artists.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.shahin.deezer.data.models.artist.Artist
import com.shahin.deezer.data.services.ArtistsApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ArtistsRemoteSourceImpl @Inject constructor(
    private val service: ArtistsApi,
) : ArtistsRemoteSource {

    override fun search(artistName: String): Flow<PagingData<Artist>> {
        return Pager(
            config = PagingConfig(
                pageSize = 25,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                ArtistsPagingDataSource(
                    service,
                    artistName
                )
            }
        ).flow.map {
            it.insertHeaderItem(
                Artist()
            )
        }
    }
}