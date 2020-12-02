/*
 * Copyright (c) 2020. By Shahin Montazeri (shahin.montazeri@gmail.com) 
 * Programmed for demonstration purposes
 */

package com.shahin.deezer.data.models.tracks

import androidx.annotation.Keep
import com.shahin.deezer.ui.fragments.tracks.adapter.TracksAdapter

/**
 * A Shell of our Track Model
 * The Response of TracksApi is not returning any Album ID value
 * which was needed to manage cache in
 * @see TracksPagingDataSource
 *
 * For validating TrackShells based on their AlbumID
 * @param albumId was so needed
 * Please refer to
 * @see TracksPagingDataSource
 */
@Keep
data class TrackShell(
    val track: Track,
    val albumId: String? = null
)