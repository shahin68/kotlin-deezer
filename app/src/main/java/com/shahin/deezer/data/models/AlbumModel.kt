package com.shahin.deezer.data.models

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class AlbumModel(
    @SerializedName("id") val id: Long,
    @SerializedName("albumName") val albumName: String,
    @SerializedName("albumArtist") val albumArtist: String,
    @SerializedName("albumImage") val albumImage: String
)