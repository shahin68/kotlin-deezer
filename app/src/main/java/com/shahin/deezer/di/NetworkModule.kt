/*
 * Copyright (c) 2020. By Shahin Montazeri (shahin.montazeri@gmail.com) 
 * Programmed for demonstration purposes
 */

package com.shahin.deezer.di

import com.shahin.deezer.network.HttpInterceptor
import com.shahin.deezer.network.WrapperConverterFactory
import com.google.gson.GsonBuilder
import com.shahin.deezer.BuildConfig
import com.shahin.deezer.data.sources.localsource.LocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Main Network Module
 * Responsible for creating and providing Networking
 * Proving OkHttp, ConverterFactory, Interceptors and Other Configurations
 */
@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        // no need to use our factory anymore
        /*converterFactory: Converter.Factory*/
    ): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        httpInterceptor: HttpInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(httpInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    /**
     * making sure log interceptor only runs on debug mode - preventing production compromise
     */
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        } else {
            logging.setLevel(HttpLoggingInterceptor.Level.NONE)
        }
        return logging
    }

    @Provides
    fun provideAuthInterceptor(
        localDataSource: LocalDataSource
    ): HttpInterceptor {
        return HttpInterceptor(localDataSource)
    }

    @Provides
    fun provideConverterFactory(
        gsonConverterFactory: GsonConverterFactory
    ): Converter.Factory {
        return WrapperConverterFactory(gsonConverterFactory)
    }

    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory {
        val gson = GsonBuilder()
            .setLenient()
            .create()
        return GsonConverterFactory.create(gson)
    }

}