/*
 * Copyright (c) 2020. By Shahin Montazeri (shahin.montazeri@gmail.com
 */

package com.shahin.deezer.data.models.artist

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
	val data: List<Artist>,

	@field:SerializedName("prev")
	val prev: String
) : Parcelable