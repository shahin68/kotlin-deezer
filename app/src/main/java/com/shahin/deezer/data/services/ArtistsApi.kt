/*
 * Copyright (c) 2020. By Shahin Montazeri (shahin.montazeri@gmail.com
 */

package com.shahin.deezer.data.services

import com.shahin.deezer.data.models.album.Album
import com.shahin.deezer.data.models.artist.Artist
import com.shahin.deezer.data.models.artist.ArtistResponse
import com.shahin.deezer.data.models.search.DataItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Artists Api Interface providing
 * @see generalSearch General Search
 * @see queryForArtists Artist Search
 */
interface ArtistsApi {

    @GET("search")
    @Headers(
        "No-Locality: true"
    )
    suspend fun generalSearch(
        @Query("q") artistName: String,
        @Query("order") order: String = OrderType.RANKING.name,
        @Query("index") page: Int,
        @Query("limit") limit: Int = 25
    ): Response<List<DataItem>>

    @GET("search/artist")
    @Headers(
        "No-Locality: true"
    )
    suspend fun queryForArtists(
        @Query("q") artistName: String,
        @Query("order") order: String = OrderType.RANKING.name,
        @Query("index") page: Int,
        @Query("limit") limit: Int = 25
    ): Response<ArtistResponse>

}