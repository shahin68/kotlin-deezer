package com.shahin.deezer.network

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class WrapperResponse<T>(
    @SerializedName("items") val body: T
)