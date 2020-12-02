/*
 * Copyright (c) 2020. By Shahin Montazeri (shahin.montazeri@gmail.com) 
 * Programmed for demonstration purposes
 */

package com.shahin.deezer.data.sources.artists.remote

import androidx.paging.PagingData
import com.shahin.deezer.data.models.artist.Artist
import kotlinx.coroutines.flow.Flow

interface ArtistsRemoteSource {
    fun search(artistName: String): Flow<PagingData<Artist>>
}