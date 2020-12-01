package com.shahin.deezer.data.services

import com.shahin.deezer.data.models.tracks.Track
import com.shahin.deezer.data.models.tracks.TracksResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface TracksApi {

    @GET("album/{id}/tracks")
    @Headers(
        "No-Locality: true"
    )
    suspend fun queryForTracks(
        @Path("id") albumId: String? = "",
        @Query("order") order: String = OrderType.RANKING.name,
        @Query("index") page: Int,
        @Query("limit") limit: Int = 25
    ): Response<TracksResponse>

}