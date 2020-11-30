package com.shahin.deezer.data.sources.albums.remote

import androidx.paging.PagingSource
import com.shahin.deezer.data.models.album.Album
import com.shahin.deezer.data.services.AlbumsApi
import retrofit2.HttpException
import java.io.IOException

private const val STARTING_PAGE_INDEX = 1

class AlbumsPagingDataSource(
    private val service: AlbumsApi,
    private val artistId: String?
) : PagingSource<Int, Album>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Album> {
        val position = params.key ?: STARTING_PAGE_INDEX
        val apiQuery = artistId
        return try {
            val response = service.queryForAlbums(apiQuery, position, params.loadSize)
            val results = response.body()!!
            LoadResult.Page(
                data = results,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (results.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun invalidate() {
        super.invalidate()

    }
}