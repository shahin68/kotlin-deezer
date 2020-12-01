package com.shahin.deezer.data.models.artist

import androidx.annotation.Keep
import com.shahin.deezer.ui.fragments.search.adapter.ArtistsAdapter

@Keep
data class ArtistShell(
    val artist: Artist,
    val viewType: ArtistsAdapter.ViewTypes
)