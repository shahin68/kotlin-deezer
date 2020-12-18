/*
 * Copyright (c) 2020. By Shahin Montazeri (shahin.montazeri@gmail.com)
 * Programmed for demonstration purposes
 */

package com.shahin.deezer.data.sources.remote.paging

import android.os.Parcelable
import com.shahin.deezer.data.models.ResponseModel
import com.shahin.deezer.data.models.album.Album
import com.shahin.deezer.data.models.artist.Artist
import com.shahin.deezer.data.models.search.SearchItem
import com.shahin.deezer.data.models.tracks.Track
import com.shahin.deezer.data.services.DeezerApi
import com.shahin.deezer.data.services.ServiceType
import retrofit2.Response
import javax.inject.Inject

class PagingDataSourceImpl @Inject constructor(
    private val service: DeezerApi
): PagingDataSource {
    /*override suspend fun generalSearch(
        query: String,
        page: Int,
        pageLimit: Int
    ): Response<ResponseModel<SearchItem>> {
        return service.generalSearch(
            artistName = query,
            page = page,
            limit = pageLimit)
    }*/

    override suspend fun fetchArtist(
        query: String,
        page: Int,
        pageLimit: Int
    ): Response<ResponseModel<Artist>> {
        return service.queryForArtists(
            artistName = query,
            page = page,
            limit = pageLimit)
    }

    override suspend fun fetchAlbum(
        query: String,
        page: Int,
        pageLimit: Int
    ): Response<ResponseModel<Album>> {
        return service.queryForAlbums(
            artistId = query,
            page = page,
            limit = pageLimit)
    }

    override suspend fun fetchTrack(
        query: String,
        page: Int,
        pageLimit: Int
    ): Response<ResponseModel<Track>> {
        return service.queryForTracks(
            albumId = query,
            page = page,
            limit = pageLimit)
    }

}