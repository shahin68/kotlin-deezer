/*
 * Copyright (c) 2020. By Shahin Montazeri (shahin.montazeri@gmail.com) 
 * Programmed for demonstration purposes
 */

package com.shahin.deezer.data.models.album

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Album Response Envelope
 * @param total was needed in
 * @see AlbumsPagingDataSource
 */
@Parcelize
data class AlbumResponse(

	@field:SerializedName("next")
	val next: String,

	@field:SerializedName("total")
	val total: Int,

	@field:SerializedName("data")
	val data: List<Album>
) : Parcelable
