package com.shahin.deezer.data.sources.artists.remote

import androidx.paging.PagingSource
import com.shahin.deezer.data.models.artist.Artist
import com.shahin.deezer.data.services.ArtistsApi
import retrofit2.HttpException
import java.io.IOException

class ArtistsPagingDataSource(
    private val service: ArtistsApi,
    private val query: String
) : PagingSource<Int, Artist>() {

    private val startPage = 0
    private val limit = 25

    companion object {
        private val inMemoryCache = mutableListOf<Artist>()
        private val queryCache = mutableListOf<String>()
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Artist> {
        val position = params.key ?: startPage
        val apiQuery = query
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
                val response = service.queryForArtists(
                    artistName = apiQuery,
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


    private fun resultsValidatedAndSorted(query: String): List<Artist> {
        return inMemoryCache.filter {
            it.name?.contains(query, true) ?: false
        }
    }

    override fun invalidate() {
        super.invalidate()
        inMemoryCache.clear()
        queryCache.clear()
    }
}