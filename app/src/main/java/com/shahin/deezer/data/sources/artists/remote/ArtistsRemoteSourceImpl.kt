package com.shahin.deezer.data.sources.artists.remote

import com.shahin.deezer.data.models.ArtistModel
import com.shahin.deezer.data.sources.BaseRepository
import com.shahin.deezer.network.NetworkResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ArtistsRemoteSourceImpl @Inject constructor(
    private val artistsApi: ArtistsApi
): BaseRepository(), ArtistsRemoteSource {
    override suspend fun search(artistName: String): NetworkResponse<List<ArtistModel>> {
        return withContext(Dispatchers.IO) {
            return@withContext networkResponseOf {
                artistsApi.query(
                    artistName
                )
            }
        }
    }
}