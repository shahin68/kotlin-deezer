/*
 * Copyright (c) 2020. By Shahin Montazeri (shahin.montazeri@gmail.com) 
 * Programmed for demonstration purposes
 */

package com.shahin.deezer.di

import android.os.Parcelable
import com.shahin.deezer.data.models.album.Album
import com.shahin.deezer.data.models.artist.Artist
import com.shahin.deezer.data.models.tracks.Track
import com.shahin.deezer.data.sources.remote.Repository
import com.shahin.deezer.data.sources.remote.RepositoryImpl
import com.shahin.deezer.data.sources.remote.paging.PagingDataSource
import com.shahin.deezer.data.sources.remote.paging.PagingDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindPagingSource(
        pagingDataSourceImpl: PagingDataSourceImpl
    ): PagingDataSource

    @Binds
    abstract fun bindRepository(
        repositoryImpl: RepositoryImpl
    ): Repository

}