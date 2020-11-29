package com.shahin.deezer.data.sources.artists.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.shahin.deezer.api.ArtistsApi
import com.shahin.deezer.data.models.search.DataItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

private const val NETWORK_PAGE_SIZE = 25

class ArtistsRemoteSourceImpl @Inject constructor(
    private val service: ArtistsApi
) : ArtistsRemoteSource {
    override fun search(artistName: String): Flow<PagingData<DataItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { ArtistsPagingDataSource(service, artistName) }
        ).flow
    }

}