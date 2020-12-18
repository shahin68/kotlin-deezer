/*
 * Copyright (c) 2020. By Shahin Montazeri (shahin.montazeri@gmail.com)
 * Programmed for demonstration purposes
 */

package com.shahin.deezer.data.sources.remote

import android.os.Parcelable
import androidx.paging.PagingData
import com.shahin.deezer.data.models.ResponseData
import com.shahin.deezer.data.services.ServiceType
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun fetch(query: String, serviceType: ServiceType): Flow<PagingData<ResponseData>>
}