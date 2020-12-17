/*
 * Copyright (c) 2020. By Shahin Montazeri (shahin.montazeri@gmail.com) 
 * Programmed for demonstration purposes
 */

package com.shahin.deezer.data.sources.albums

import androidx.paging.PagingData
import com.shahin.deezer.data.models.ResponseData
import com.shahin.deezer.data.models.album.Album
import kotlinx.coroutines.flow.Flow

interface AlbumsRepository {
    fun fetchAlbums(
        artistId: String
    ): Flow<PagingData<ResponseData<Album>>>
}