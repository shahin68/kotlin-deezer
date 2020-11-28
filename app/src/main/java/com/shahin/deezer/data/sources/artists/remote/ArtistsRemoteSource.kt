package com.shahin.deezer.data.sources.artists.remote

import com.shahin.deezer.data.models.ArtistModel
import com.shahin.deezer.network.NetworkResponse

interface ArtistsRemoteSource {
    suspend fun search(artistName: String): NetworkResponse<List<ArtistModel>>
}