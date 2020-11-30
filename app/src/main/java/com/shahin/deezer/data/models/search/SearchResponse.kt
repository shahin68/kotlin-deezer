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
