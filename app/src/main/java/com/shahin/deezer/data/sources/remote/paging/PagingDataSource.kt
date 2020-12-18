/*
 * Copyright (c) 2020. By Shahin Montazeri (shahin.montazeri@gmail.com)
 * Programmed for demonstration purposes
 */

package com.shahin.deezer.data.sources.remote.paging

import com.shahin.deezer.data.models.ResponseModel
import com.shahin.deezer.data.models.album.Album
import com.shahin.deezer.data.models.artist.Artist
import com.shahin.deezer.data.models.search.SearchItem
import com.shahin.deezer.data.models.tracks.Track
import com.shahin.deezer.data.services.ServiceType
import retrofit2.Response

interface PagingDataSource {
//    suspend fun generalSearch(query: String, page : Int, pageLimit: Int) : Response<ResponseModel<SearchItem>>
    suspend fun fetchArtist(query: String, page : Int, pageLimit: Int) : Response<ResponseModel<Artist>>
    suspend fun fetchAlbum(query: String, page : Int, pageLimit: Int) : Response<ResponseModel<Album>>
    suspend fun fetchTrack(query: String, page : Int, pageLimit: Int) : Response<ResponseModel<Track>>
}