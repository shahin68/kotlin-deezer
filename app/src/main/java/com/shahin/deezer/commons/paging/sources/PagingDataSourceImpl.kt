/*
 * Copyright (c) 2020. By Shahin Montazeri (shahin.montazeri@gmail.com)
 * Programmed for demonstration purposes
 */

package com.shahin.deezer.commons.paging.sources

import com.shahin.deezer.data.models.ResponseModel
import com.shahin.deezer.data.services.DeezerApi
import com.shahin.deezer.data.services.ServiceType
import retrofit2.Response
import javax.inject.Inject

class PagingDataSourceImpl<T> @Inject constructor(
    private val service: DeezerApi
): PagingDataSource<T> {

    override suspend fun fetch(query: String, page: Int, pageLimit: Int, serviceType: ServiceType): Response<ResponseModel<T>> {
        return when (serviceType) {
            ServiceType.Artists -> {
                service.queryForArtists(
                    artistName = query,
                    page = page,
                    limit = pageLimit) as Response<ResponseModel<T>>
            }
            ServiceType.Albums -> {
                service.queryForAlbums(
                    artistId = query,
                    page = page,
                    limit = pageLimit) as Response<ResponseModel<T>>
            }
            ServiceType.Tracks -> {
                service.queryForTracks(
                    albumId = query,
                    page = page,
                    limit = pageLimit) as Response<ResponseModel<T>>
            }
        }
    }

}