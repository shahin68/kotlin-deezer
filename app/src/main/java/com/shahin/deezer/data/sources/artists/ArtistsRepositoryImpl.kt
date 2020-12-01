package com.shahin.deezer.data.sources.artists

import androidx.paging.PagingData
import com.shahin.deezer.data.models.artist.Artist
import com.shahin.deezer.data.sources.artists.local.ArtistsLocalSource
import com.shahin.deezer.data.sources.artists.remote.ArtistsRemoteSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ArtistsRepositoryImpl @Inject constructor(
    private val artistsLocalSource: ArtistsLocalSource,
    private val artistsRemoteSource: ArtistsRemoteSource
) : ArtistsRepository {
    override fun search(artistName: String): Flow<PagingData<Artist>> {
        return artistsRemoteSource.search(artistName)
    }

}