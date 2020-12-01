/*
 * Copyright (c) 2020. By Shahin Montazeri (shahin.montazeri@gmail.com
 */

package com.shahin.deezer.data.models.tracks

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TracksResponse(

	@field:SerializedName("total")
	val total: Int,

	@field:SerializedName("data")
	val data: List<Track>
) : Parcelable


