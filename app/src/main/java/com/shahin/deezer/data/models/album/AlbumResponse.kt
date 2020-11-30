package com.shahin.deezer.data.models.album

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AlbumResponse(

	@field:SerializedName("next")
	val next: String,

	@field:SerializedName("total")
	val total: Int,

	@field:SerializedName("data")
	val data: List<Album?>
) : Parcelable
