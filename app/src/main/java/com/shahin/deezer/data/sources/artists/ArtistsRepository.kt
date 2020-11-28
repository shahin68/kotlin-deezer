package com.shahin.deezer.data.sources.artists

import com.shahin.deezer.data.models.ArtistModel
import com.shahin.deezer.network.NetworkResponse

interface ArtistsRepository {
    suspend fun search(
        artistName: String
    ): NetworkResponse<List<ArtistModel>>
}