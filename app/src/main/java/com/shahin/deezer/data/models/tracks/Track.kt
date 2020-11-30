package com.shahin.deezer.data.models.tracks

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Track(

    @field:SerializedName("readable")
    val readable: Boolean,

    @field:SerializedName("preview")
    val preview: String,

    @field:SerializedName("md5_image")
    val md5Image: String,

    @field:SerializedName("artist")
    val artist: TracksArtist,

    @field:SerializedName("link")
    val link: String,

    @field:SerializedName("explicit_content_cover")
    val explicitContentCover: Int,

    @field:SerializedName("isrc")
    val isrc: String,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("title_version")
    val titleVersion: String,

    @field:SerializedName("explicit_lyrics")
    val explicitLyrics: Boolean,

    @field:SerializedName("type")
    val type: String,

    @field:SerializedName("title_short")
    val titleShort: String,

    @field:SerializedName("duration")
    val duration: String,

    @field:SerializedName("disk_number")
    val diskNumber: Int,

    @field:SerializedName("rank")
    val rank: String,

    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("explicit_content_lyrics")
    val explicitContentLyrics: Int,

    @field:SerializedName("track_position")
    val trackPosition: Int
) : Parcelable

@Parcelize
data class TracksArtist(

    @field:SerializedName("tracklist")
    val tracklist: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("type")
    val type: String
) : Parcelable