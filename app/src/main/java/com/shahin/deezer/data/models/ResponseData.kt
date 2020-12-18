/*
 * Copyright (c) 2020. By Shahin Montazeri (shahin.montazeri@gmail.com)
 * Programmed for demonstration purposes
 */

package com.shahin.deezer.data.models

import android.os.Parcelable
import androidx.annotation.Keep

@Keep
data class ResponseData(
    val query: String,
    val data: Any
)