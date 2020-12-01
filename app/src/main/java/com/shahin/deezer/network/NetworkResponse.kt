/*
 * Copyright (c) 2020. By Shahin Montazeri (shahin.montazeri@gmail.com
 */

package com.shahin.deezer.network

import com.google.gson.annotations.SerializedName
import com.shahin.deezer.data.models.Error

sealed class NetworkResponse<out T> {
    data class Success<out T>(@SerializedName("data") val data: T): NetworkResponse<T>()
    data class GenericError(
        @SerializedName("error") val error: Error
    ): NetworkResponse<Nothing>()
    object NetworkError: NetworkResponse<Nothing>()
}