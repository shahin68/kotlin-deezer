package com.shahin.deezer.data.services

import com.shahin.deezer.data.models.album.Album
import com.shahin.deezer.data.models.album.AlbumResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface AlbumsApi {

    @GET("artist/{id}/albums")
    @Headers(
        "No-Locality: true"
    )
    suspend fun queryForAlbums(
        @Path("id") artistId: String? = "",
        @Query("order") order: String = OrderType.RANKING.name,
        @Query("index") page: Int,
        @Query("limit") limit: Int = 25
    ): Response<AlbumResponse>

}