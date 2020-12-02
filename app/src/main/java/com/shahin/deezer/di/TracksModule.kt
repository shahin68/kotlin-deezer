/*
 * Copyright (c) 2020. By Shahin Montazeri (shahin.montazeri@gmail.com) 
 * Programmed for demonstration purposes
 */

package com.shahin.deezer.di

import com.shahin.deezer.data.sources.tracks.TracksRepository
import com.shahin.deezer.data.sources.tracks.TracksRepositoryImpl
import com.shahin.deezer.data.sources.tracks.local.TracksLocalSource
import com.shahin.deezer.data.sources.tracks.local.TracksLocalSourceImpl
import com.shahin.deezer.data.sources.tracks.remote.TracksRemoteSource
import com.shahin.deezer.data.sources.tracks.remote.TracksRemoteSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

/**
 * Module for binding Track repositories
 */
@Module
@InstallIn(ApplicationComponent::class)
abstract class TracksModule {
    @Binds
    abstract fun bindTracksRemoteDataSource(
        albumsRemoteSourceImpl: TracksRemoteSourceImpl
    ): TracksRemoteSource

    @Binds
    abstract fun bindTracksLocalDataSource(
        albumsLocalSourceImpl: TracksLocalSourceImpl
    ): TracksLocalSource

    @Binds
    abstract fun bindTracksRepository(
        albumsRepositoryImpl: TracksRepositoryImpl
    ): TracksRepository
}