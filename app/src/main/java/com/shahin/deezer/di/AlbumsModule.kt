/*
 * Copyright (c) 2020. By Shahin Montazeri (shahin.montazeri@gmail.com) 
 * Programmed for demonstration purposes
 */

package com.shahin.deezer.di

import com.shahin.deezer.data.sources.albums.AlbumsRepository
import com.shahin.deezer.data.sources.albums.AlbumsRepositoryImpl
import com.shahin.deezer.data.sources.albums.local.AlbumsLocalSource
import com.shahin.deezer.data.sources.albums.local.AlbumsLocalSourceImpl
import com.shahin.deezer.data.sources.albums.remote.AlbumsRemoteSource
import com.shahin.deezer.data.sources.albums.remote.AlbumsRemoteSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

/**
 * Module for binding Album repositories
 */
@Module
@InstallIn(ApplicationComponent::class)
abstract class AlbumsModule {
    @Binds
    abstract fun bindAlbumsRemoteDataSource(
        albumsRemoteSourceImpl: AlbumsRemoteSourceImpl
    ): AlbumsRemoteSource

    @Binds
    abstract fun bindAlbumsLocalDataSource(
        albumsLocalSourceImpl: AlbumsLocalSourceImpl
    ): AlbumsLocalSource

    @Binds
    abstract fun bindAlbumsRepository(
        albumsRepositoryImpl: AlbumsRepositoryImpl
    ): AlbumsRepository
}