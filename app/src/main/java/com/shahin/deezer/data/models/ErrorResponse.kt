/*
 * Copyright (c) 2020. By Shahin Montazeri (shahin.montazeri@gmail.com) 
 * Programmed for demonstration purposes
 */

package com.shahin.deezer.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Error Response
 * To be used for Error Handling with Non Paging Api calls
 */
@Parcelize
data class ErrorResponse(

	@field:SerializedName("error")
	val error: Error? = null
) : Parcelable

/**
 * Actual Deezer Error Model
 */
@Parcelize
data class Error(

	@field:SerializedName("code")
	val code: Int,

	@field:SerializedName("type")
	val type: String,

	@field:SerializedName("message")
	val message: String
) : Parcelable
