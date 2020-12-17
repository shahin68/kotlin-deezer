/*
 * Copyright (c) 2020. By Shahin Montazeri (shahin.montazeri@gmail.com) 
 * Programmed for demonstration purposes
 */

package com.shahin.deezer.di

import com.shahin.deezer.data.services.DeezerApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit

/**
 * Main App Module
 * Providing Main functionality for the program
 */
@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides
    fun provideArtistsApi(
        retrofit: Retrofit
    ): DeezerApi {
        return retrofit.create(DeezerApi::class.java)
    }
}