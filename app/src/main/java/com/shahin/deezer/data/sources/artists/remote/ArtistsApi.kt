package com.shahin.deezer.data.sources.artists.remote

import com.shahin.deezer.data.models.ArtistsResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ArtistsApi {

    @GET("customsearch/v1")
    suspend fun query(
        @Query("artistName") artistName: String
    ): Response<List<ArtistsResponseModel>>

}