/*
 * Copyright (c) 2020. By Shahin Montazeri (shahin.montazeri@gmail.com) 
 * Programmed for demonstration purposes
 */

package com.shahin.deezer.network

import com.google.gson.annotations.SerializedName
import com.shahin.deezer.data.models.Error

/**
 * Class defining a Network Response
 * To be used to parse Non-Paging Api Calls
 */
sealed class NetworkResponse<out T> {
    data class Success<out T>(@SerializedName("data") val data: T): NetworkResponse<T>()
    data class GenericError(
        @SerializedName("error") val error: Error
    ): NetworkResponse<Nothing>()
    object NetworkError: NetworkResponse<Nothing>()
}