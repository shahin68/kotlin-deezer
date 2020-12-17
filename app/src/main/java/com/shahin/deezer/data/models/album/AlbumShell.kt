/*
 * Copyright (c) 2020. By Shahin Montazeri (shahin.montazeri@gmail.com) 
 * Programmed for demonstration purposes
 */

package com.shahin.deezer.data.models.album

import androidx.annotation.Keep

/**
 * A Shell of our Album Model
 * The Response of Albums is not returning any Artist ID value
 * which was needed to manage cache in
 * @see TracksPagingDataSource
 *
 */
@Keep
data class AlbumShell(
    val album: Album,
    val artistId: String
)
