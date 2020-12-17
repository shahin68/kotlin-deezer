/*
 * Copyright (c) 2020. By Shahin Montazeri (shahin.montazeri@gmail.com)
 * Programmed for demonstration purposes
 */

package com.shahin.deezer.data.models

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

/**
 * General Response Envelope
 * @param total is needed to handle paging
 */
@Keep
data class ResponseModel<T>(

    @SerializedName("total")
    val total: Int,

    @SerializedName("data")
    val data: List<ResponseData<T>>
)