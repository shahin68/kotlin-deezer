package com.shahin.deezer.api

import com.shahin.deezer.data.models.album.Album
import com.shahin.deezer.data.models.artist.Artist
import com.shahin.deezer.data.models.search.DataItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ArtistsApi {

    @GET("search")
    @Headers(
        "No-Locality: true"
    )
    suspend fun generalSearch(
        @Query("q") artistName: String,
        @Query("index") page: Int,
        @Query("limit") limit: Int = 25
    ): Response<List<DataItem>>

    @GET("search/artist")
    @Headers(
        "No-Locality: true"
    )
    suspend fun queryForArtists(
        @Query("q") artistName: String,
        @Query("index") page: Int,
        @Query("limit") limit: Int = 25
    ): Response<List<Artist>>

    @GET("search/album")
    @Headers(
        "No-Locality: true"
    )
    suspend fun queryForAlbums(
        @Query("q") artistName: String,
        @Query("index") page: Int,
        @Query("limit") limit: Int = 25
    ): Response<List<Album>>
}