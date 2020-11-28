package com.shahin.deezer.data.models

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ArtistsResponseModel(
    @SerializedName("id") val id: String,
    @SerializedName("artistName") val artistName: String,
)