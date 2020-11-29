package com.shahin.deezer.data.sources.artists

import androidx.paging.PagingData
import com.shahin.deezer.data.models.search.DataItem
import kotlinx.coroutines.flow.Flow

interface ArtistsRepository {
    fun search(
        artistName: String
    ): Flow<PagingData<DataItem>>
}