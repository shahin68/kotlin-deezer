package com.shahin.deezer.data.models.tracks

import androidx.annotation.Keep
import com.shahin.deezer.ui.fragments.tracks.adapter.TracksAdapter

@Keep
data class TrackShell(
    val track: Track,
    val albumId: String? = null
)