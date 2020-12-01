package com.shahin.deezer.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ErrorResponse(

	@field:SerializedName("error")
	val error: Error? = null
) : Parcelable

@Parcelize
data class Error(

	@field:SerializedName("code")
	val code: Int,

	@field:SerializedName("type")
	val type: String,

	@field:SerializedName("message")
	val message: String
) : Parcelable
