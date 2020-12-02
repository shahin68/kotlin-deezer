/*
 * Copyright (c) 2020. By Shahin Montazeri (shahin.montazeri@gmail.com) 
 * Programmed for demonstration purposes
 */

package com.shahin.deezer.network

import com.shahin.deezer.data.sources.localsource.LocalDataSource
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.util.*
import javax.inject.Inject

/**
 * Network Interceptor
 * Purpose: Provide Headers for all api calls
 */
class HttpInterceptor @Inject constructor(
    private val localDataSource: LocalDataSource
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = localDataSource.getApiKey()
        val url = chain.request().url.newBuilder()
            .addQueryParameter("locale", Locale.getDefault().language)
            .build()
        val request = chain.request()
        val requestBuilder = request.newBuilder()

        /**
         * Note: add token header except for exposed api calls
         * Note: if "No-Authentication" is defined in api call
         * that means we do Not want a token header
         */
        if (request.header("No-Authentication") == null) {
            requestBuilder.addHeader("access_token", token)
        } else {
            requestBuilder.removeHeader("No-Authentication")
        }

        /**
         * Note: add locale except for a few select api calls
         * Note: if "No-Locality" is defined in api call
         * that means we do Not want to fetch a localized response
         */
        if (request.header("No-Locality") == null) {
            requestBuilder.url(url)
        } else {
            requestBuilder.removeHeader("No-Locality")
        }
        val newRequest = requestBuilder.build()
        return try {
            chain.proceed(newRequest)
        } catch (e: Exception) {
            throw IOException(e.message)
        }
    }
}
