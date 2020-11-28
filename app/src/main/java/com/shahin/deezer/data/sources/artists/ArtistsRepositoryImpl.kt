package com.shahin.deezer.data.sources.artists

import com.shahin.deezer.data.models.ArtistsResponseModel
import com.shahin.deezer.data.sources.artists.local.ArtistsLocalSource
import com.shahin.deezer.data.sources.artists.remote.ArtistsRemoteSource
import com.shahin.deezer.network.NetworkResponse
import javax.inject.Inject

class ArtistsRepositoryImpl @Inject constructor(
    private val artistsLocalSource: ArtistsLocalSource,
    private val artistsRemoteSource: ArtistsRemoteSource
) : ArtistsRepository {
    override suspend fun search(artistName: String): NetworkResponse<List<ArtistsResponseModel>> {
        return artistsRemoteSource.search(artistName)
    }
}