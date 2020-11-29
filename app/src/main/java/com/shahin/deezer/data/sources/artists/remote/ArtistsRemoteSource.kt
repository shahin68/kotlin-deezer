package com.shahin.deezer.data.sources.artists.remote

import androidx.paging.PagingData
import com.shahin.deezer.data.models.search.DataItem
import kotlinx.coroutines.flow.Flow

interface ArtistsRemoteSource {
    fun search(artistName: String): Flow<PagingData<DataItem>>
}