/*
 * Copyright (c) 2020. By Shahin Montazeri (shahin.montazeri@gmail.com)
 * Programmed for demonstration purposes
 */

package com.shahin.deezer.data.models

data class ResponseData<T>(
    val query: String,
    val data: T
)