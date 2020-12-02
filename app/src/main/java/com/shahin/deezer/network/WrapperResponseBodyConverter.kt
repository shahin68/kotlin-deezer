/*
 * Copyright (c) 2020. By Shahin Montazeri (shahin.montazeri@gmail.com) 
 * Programmed for demonstration purposes
 */

package com.shahin.deezer.network

import okhttp3.ResponseBody
import retrofit2.Converter

/**
 * Response Body Wrapper
 * Converting Any Response to
 * @see WrapperResponse body type
 */
class WrapperResponseBodyConverter<T>(
    private val converter: Converter<ResponseBody, WrapperResponse<T>>
): Converter<ResponseBody, T> {
    override fun convert(value: ResponseBody): T? {
        val response: WrapperResponse<T>? = converter.convert(value)
        return response?.data
    }

}