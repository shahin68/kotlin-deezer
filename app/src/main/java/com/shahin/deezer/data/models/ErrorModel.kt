package com.shahin.deezer.data.models

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ErrorModel(
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String
)