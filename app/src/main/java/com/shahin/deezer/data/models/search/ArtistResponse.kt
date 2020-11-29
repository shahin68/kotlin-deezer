package com.shahin.deezer.data.models.search

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ArtistResponse(

	@field:SerializedName("next")
	val next: String,

	@field:SerializedName("total")
	val total: Int,

	@field:SerializedName("data")
	val data: List<ArtistItem?>,

	@field:SerializedName("prev")
	val prev: String
) : Parcelable

@Parcelize
data class ArtistItem(

	@field:SerializedName("picture_xl")
	val pictureXl: String,

	@field:SerializedName("tracklist")
	val tracklist: String,

	@field:SerializedName("link")
	val link: String,

	@field:SerializedName("picture_small")
	val pictureSmall: String,

	@field:SerializedName("type")
	val type: String,

	@field:SerializedName("nb_album")
	val nbAlbum: Int,

	@field:SerializedName("picture")
	val picture: String,

	@field:SerializedName("nb_fan")
	val nbFan: Int,

	@field:SerializedName("radio")
	val radio: Boolean,

	@field:SerializedName("picture_big")
	val pictureBig: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("picture_medium")
	val pictureMedium: String
) : Parcelable
