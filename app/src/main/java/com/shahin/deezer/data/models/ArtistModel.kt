package com.shahin.deezer.data.models

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ArtistModel(
    @SerializedName("id") val id: Long,
    @SerializedName("artistName") val artistName: String,
    @SerializedName("artistImage") val artistImage: String,
)