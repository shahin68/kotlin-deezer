package com.shahin.deezer.data.services

import androidx.annotation.Keep

@Keep
enum class OrderType {
    RANKING,
    TRACK_ASC,
    TRACK_DESC,
    ARTIST_ASC,
    ARTIST_DESC,
    ALBUM_ASC,
    ALBUM_DESC,
    RATING_ASC,
    RATING_DESC,
    DURATION_ASC,
    DURATION_DESC
}