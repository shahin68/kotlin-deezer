/*
 * Copyright (c) 2020. By Shahin Montazeri (shahin.montazeri@gmail.com) 
 * Programmed for demonstration purposes
 */

package com.shahin.deezer.data.models.artist

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Actual Artist Model we care about
 */
@Parcelize
data class Artist(

    @field:SerializedName("picture_xl")
    val pictureXl: String? = null,

    @field:SerializedName("tracklist")
    val tracklist: String? = null,

    @field:SerializedName("link")
    val link: String? = null,

    @field:SerializedName("picture_small")
    val pictureSmall: String? = null,

    @field:SerializedName("type")
    val type: String? = null,

    @field:SerializedName("nb_album")
    val nbAlbum: Int? = null,

    @field:SerializedName("picture")
    val picture: String? = null,

    @field:SerializedName("nb_fan")
    val nbFan: Int? = null,

    @field:SerializedName("radio")
    val radio: Boolean? = null,

    @field:SerializedName("picture_big")
    val pictureBig: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("picture_medium")
    val pictureMedium: String? = null
) : Parcelable