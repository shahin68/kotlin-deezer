package com.shahin.deezer.di

import com.shahin.deezer.data.services.AlbumsApi
import com.shahin.deezer.data.services.ArtistsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides
    fun provideArtistsApi(
        retrofit: Retrofit
    ): ArtistsApi {
        return retrofit.create(ArtistsApi::class.java)
    }

    @Provides
    fun provideAlbumsApi(
        retrofit: Retrofit
    ): AlbumsApi {
        return retrofit.create(AlbumsApi::class.java)
    }
}