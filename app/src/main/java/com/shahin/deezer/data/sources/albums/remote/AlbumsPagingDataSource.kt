package com.shahin.deezer.data.sources.albums.remote

import androidx.paging.PagingSource
import com.shahin.deezer.data.models.album.Album
import com.shahin.deezer.data.services.AlbumsApi
import retrofit2.HttpException
import java.io.IOException

class AlbumsPagingDataSource(
    private val service: AlbumsApi,
    private val artistId: String
) : PagingSource<Int, Album>() {

    private val startPage = 0
    private val limit = 25

    companion object {
        private val inMemoryCache = mutableListOf<Album>()
        private val queryCache = mutableListOf<String>()
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Album> {
        val position = params.key ?: startPage
        val apiQuery = artistId
        return try {

            val oldQuery = queryCache.find {
                apiQuery.equals(it, true)
            }

            if (oldQuery != null && position == startPage) {
                val validatedResults = resultsValidatedAndSorted(apiQuery)
                LoadResult.Page(
                    data = validatedResults,
                    prevKey = null,
                    nextKey = null
                )
            } else {
                val response = service.queryForAlbums(
                    artistId = apiQuery,
                    page = position*limit,
                    limit = limit
                )
                val body = response.body()!!
                val results = response.body()!!.data


                queryCache.add(apiQuery)

                inMemoryCache.addAll(
                    results
                )
                LoadResult.Page(
                    data = if (position * limit <= body.total) results else emptyList(),
                    prevKey = if (position == startPage) null else position - 1,
                    nextKey = if (results.isEmpty() || position * limit > body.total) null else position + 1
                )
            }
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    private fun resultsValidatedAndSorted(artistId: String?): List<Album> {
        return inMemoryCache.filter {
            it.artist?.id.equals(artistId, false)
        }
    }

    override fun invalidate() {
        super.invalidate()
        inMemoryCache.clear()
        queryCache.clear()
    }
}