package com.shahin.deezer.data.models

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class TrackModel(
    @SerializedName("id") val id: Long,
    @SerializedName("trackTitle") val trackTitle: String,
    @SerializedName("trackArtistName") val trackArtistName: String,
)
