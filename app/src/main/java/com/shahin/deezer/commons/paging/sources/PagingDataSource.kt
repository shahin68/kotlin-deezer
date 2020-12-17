/*
 * Copyright (c) 2020. By Shahin Montazeri (shahin.montazeri@gmail.com)
 * Programmed for demonstration purposes
 */

package com.shahin.deezer.commons.paging.sources

import com.shahin.deezer.data.models.ResponseModel
import com.shahin.deezer.data.services.ServiceType
import retrofit2.Response

interface PagingDataSource<T> {
    suspend fun fetch(query: String, page : Int, pageLimit: Int, serviceType: ServiceType) : Response<ResponseModel<T>>
}