/*
 * Copyright (c) 2020. By Shahin Montazeri (shahin.montazeri@gmail.com) 
 * Programmed for demonstration purposes
 */

package com.shahin.deezer.di

import com.shahin.deezer.data.sources.local.LocalDataSource
import com.shahin.deezer.data.sources.local.LocalDataSourceImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

/**
 * Local Module
 * Providing Local Repository Interfaces
 */
@Module
@InstallIn(ApplicationComponent::class)
abstract class LocalModule {

    @Binds
    abstract fun bindLocalDataSource(
        localDataSourceImp: LocalDataSourceImp
    ): LocalDataSource

}