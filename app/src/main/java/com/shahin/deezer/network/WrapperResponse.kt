/*
 * Copyright (c) 2020. By Shahin Montazeri (shahin.montazeri@gmail.com
 */

package com.shahin.deezer.network

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

/**
 * A Response Wrapper
 * This Wrapper Can be Used to Parse Server Response without using
 * an Envelope to encase the response
 *
 * CAUTION: Converter Must be provided to the Retrofit Before
 * default GsonConverterFactory
 */
@Keep
data class WrapperResponse<T>(
    @SerializedName("data") val data: T
)