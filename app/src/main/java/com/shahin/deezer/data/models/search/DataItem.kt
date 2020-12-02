/*
 * Copyright (c) 2020. By Shahin Montazeri (shahin.montazeri@gmail.com) 
 * Programmed for demonstration purposes
 */

package com.shahin.deezer.data.models.search

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.shahin.deezer.data.models.album.Album
import com.shahin.deezer.data.models.artist.Artist
import kotlinx.android.parcel.Parcelize

/**
 * General Search Response Model
 */
@Parcelize
data class DataItem(

    @field:SerializedName("readable")
    val readable: Boolean? = null,

    @field:SerializedName("preview")
    val preview: String? = null,

    @field:SerializedName("md5_image")
    val md5Image: String? = null,

    @field:SerializedName("artist")
    val artist: Artist,

    @field:SerializedName("album")
    val album: Album,

    @field:SerializedName("link")
    val link: String = "",

    @field:SerializedName("explicit_content_cover")
    val explicitContentCover: Int? = null,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("title_version")
    val titleVersion: String? = null,

    @field:SerializedName("explicit_lyrics")
    val explicitLyrics: Boolean? = null,

    @field:SerializedName("type")
    val type: String? = null,

    @field:SerializedName("title_short")
    val titleShort: String? = null,

    @field:SerializedName("duration")
    val duration: Int? = null,

    @field:SerializedName("rank")
    val rank: Int,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("explicit_content_lyrics")
    val explicitContentLyrics: Int? = null
) : Parcelable