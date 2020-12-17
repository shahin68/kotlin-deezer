/*
 * Copyright (c) 2020. By Shahin Montazeri (shahin.montazeri@gmail.com) 
 * Programmed for demonstration purposes
 */

package com.shahin.deezer.di

import com.shahin.deezer.commons.paging.sources.PagingDataSource
import com.shahin.deezer.commons.paging.sources.PagingDataSourceImpl
import com.shahin.deezer.data.models.artist.Artist
import com.shahin.deezer.data.sources.artists.ArtistsRepository
import com.shahin.deezer.data.sources.artists.ArtistsRepositoryImpl
import com.shahin.deezer.data.sources.artists.local.ArtistsLocalSource
import com.shahin.deezer.data.sources.artists.local.ArtistsLocalSourceImpl
import com.shahin.deezer.data.sources.artists.remote.ArtistsRemoteSource
import com.shahin.deezer.data.sources.artists.remote.ArtistsRemoteSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

/**
 * Module for binding Artist repositories
 */
@Module
@InstallIn(ApplicationComponent::class)
abstract class ArtistsModule {
    @Binds
    abstract fun bindArtistsRemoteDataSource(
        artistsRemoteSourceImpl: ArtistsRemoteSourceImpl
    ): ArtistsRemoteSource

    @Binds
    abstract fun bindArtistsLocalDataSource(
        artistsLocalSourceImpl: ArtistsLocalSourceImpl
    ): ArtistsLocalSource

    @Binds
    abstract fun bindArtistsRepository(
        artistsRepositoryImpl: ArtistsRepositoryImpl
    ): ArtistsRepository

    @Binds
    abstract fun makeArtistsPagingSource(
        artistPagingDataSourceImpl: PagingDataSourceImpl<Artist>
    ): PagingDataSource<Artist>
}