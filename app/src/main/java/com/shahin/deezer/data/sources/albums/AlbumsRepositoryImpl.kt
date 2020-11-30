package com.shahin.deezer.data.sources.albums

import androidx.paging.PagingData
import com.shahin.deezer.data.models.album.Album
import com.shahin.deezer.data.sources.albums.local.AlbumsLocalSource
import com.shahin.deezer.data.sources.albums.remote.AlbumsRemoteSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AlbumsRepositoryImpl @Inject constructor(
    private val albumsLocalSource: AlbumsLocalSource,
    private val albumsRemoteSource: AlbumsRemoteSource
): AlbumsRepository {
    override fun fetchAlbums(artistId: String?): Flow<PagingData<Album>> {
        return albumsRemoteSource.fetchAlbums(artistId)
    }

}