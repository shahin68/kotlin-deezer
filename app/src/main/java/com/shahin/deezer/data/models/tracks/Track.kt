/*
 * Copyright (c) 2020. By Shahin Montazeri (shahin.montazeri@gmail.com
 */

package com.shahin.deezer.data.models.tracks

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Actual Track model we care about
 */
@Parcelize
data class Track(

    @field:SerializedName("readable")
    val readable: Boolean? = null,

    @field:SerializedName("preview")
    val preview: String? = null,

    @field:SerializedName("md5_image")
    val md5Image: String? = null,

    @field:SerializedName("artist")
    val artist: TracksArtist? = null,

    @field:SerializedName("link")
    val link: String? = null,

    @field:SerializedName("explicit_content_cover")
    val explicitContentCover: Int? = null,

    @field:SerializedName("isrc")
    val isrc: String? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("title_version")
    val titleVersion: String? = null,

    @field:SerializedName("explicit_lyrics")
    val explicitLyrics: Boolean? = null,

    @field:SerializedName("type")
    val type: String? = null,

    @field:SerializedName("title_short")
    val titleShort: String? = null,

    @field:SerializedName("duration")
    val duration: String? = null,

    @field:SerializedName("disk_number")
    val diskNumber: Int? = null,

    @field:SerializedName("rank")
    val rank: String? = null,

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("explicit_content_lyrics")
    val explicitContentLyrics: Int? = null,

    @field:SerializedName("track_position")
    val trackPosition: Int? = null
) : Parcelable

@Parcelize
data class TracksArtist(

    @field:SerializedName("tracklist")
    val tracklist: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("type")
    val type: String
) : Parcelable