package com.shahin.deezer.network

import com.shahin.deezer.data.sources.localsource.LocalDataSource
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.util.*
import javax.inject.Inject

const val TOKEN_TYPE = "Bearer"

class HttpInterceptor @Inject constructor(
    private val localDataSource: LocalDataSource
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = localDataSource.getApiKey()
        // adding device locale to api query
        val url = chain.request().url.newBuilder()
            .addQueryParameter("locale", Locale.getDefault().language)
            .build()
        val request = chain.request()
        val requestBuilder = request.newBuilder()
        // adding token header except for exposed api calls
        if (request.header("No-Authentication") == null) {
            requestBuilder.addHeader("Authorization", "$TOKEN_TYPE $token")
        }
        val newRequest = requestBuilder.url(url).build()
        return try {
            chain.proceed(newRequest)
        } catch (e: Exception) {
            throw IOException(e.message)
        }
    }
}
