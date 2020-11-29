package com.shahin.deezer.data.models.search

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SearchResponse(

	@field:SerializedName("next")
	val next: String = "",

	@field:SerializedName("total")
	val total: Int,

	@field:SerializedName("data")
	val data: List<DataItem> = emptyList()
) : Parcelable

@Parcelize
data class Album(

	@field:SerializedName("cover")
	val cover: String,

	@field:SerializedName("md5_image")
	val md5Image: String,

	@field:SerializedName("tracklist")
	val tracklist: String,

	@field:SerializedName("cover_xl")
	val coverXl: String,

	@field:SerializedName("cover_medium")
	val coverMedium: String,

	@field:SerializedName("cover_small")
	val coverSmall: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("type")
	val type: String,

	@field:SerializedName("cover_big")
	val coverBig: String
) : Parcelable

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

@Parcelize
data class Artist(

	@field:SerializedName("picture_xl")
	val pictureXl: String,

	@field:SerializedName("tracklist")
	val tracklist: String,

	@field:SerializedName("picture_big")
	val pictureBig: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("link")
	val link: String,

	@field:SerializedName("picture_small")
	val pictureSmall: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("type")
	val type: String,

	@field:SerializedName("picture")
	val picture: String,

	@field:SerializedName("picture_medium")
	val pictureMedium: String
) : Parcelable
