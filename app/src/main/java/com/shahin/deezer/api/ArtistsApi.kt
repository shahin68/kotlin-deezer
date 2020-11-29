package com.shahin.deezer.api

import com.shahin.deezer.data.models.search.ArtistItem
import com.shahin.deezer.data.models.search.DataItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ArtistsApi {

    @GET("search")
    @Headers(
        "No-Locality: true",
        "No-Authentication: true"
    )
    suspend fun query(
        @Query("q") artistName: String,
        @Query("index") page: Int,
        @Query("limit") limit: Int = 25
    ): Response<List<DataItem>>

    @GET("search/artist")
    @Headers(
        "No-Locality: true",
        "No-Authentication: true"
    )
    suspend fun queryForArtist(
        @Query("q") artistName: String,
        @Query("index") page: Int,
        @Query("limit") limit: Int = 25
    ): Response<List<ArtistItem>>
}