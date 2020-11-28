package com.shahin.deezer.network

import okhttp3.ResponseBody
import retrofit2.Converter


class WrapperResponseBodyConverter<T>(
    private val converter: Converter<ResponseBody, WrapperResponse<T>>
): Converter<ResponseBody, T> {
    override fun convert(value: ResponseBody): T? {
        val response: WrapperResponse<T>? = converter.convert(value)
        return response?.body
    }

}