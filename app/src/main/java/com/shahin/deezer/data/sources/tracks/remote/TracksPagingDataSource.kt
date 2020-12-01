package com.shahin.deezer.data.sources.tracks.remote

import androidx.paging.PagingSource
import com.shahin.deezer.data.models.tracks.Track
import com.shahin.deezer.data.models.tracks.TrackShell
import com.shahin.deezer.data.services.TracksApi
import com.shahin.deezer.ui.fragments.tracks.adapter.TracksAdapter
import retrofit2.HttpException
import java.io.IOException

class TracksPagingDataSource(
    private val service: TracksApi,
    private val albumId: String
) : PagingSource<Int, TrackShell>() {

    private val startPage = 0
    private val limit = 25

    companion object {
        private val inMemoryCache = mutableListOf<TrackShell>()
        private val queryCache = mutableListOf<String>()
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TrackShell> {

        val position = params.key ?: startPage
        val apiQuery = albumId
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
                val response = service.queryForTracks(
                    albumId = apiQuery,
                    page = position*limit,
                    limit = limit
                )
                val body = response.body()!!
                val results = response.body()!!.data

                val shelled = results.map { TrackShell(it, apiQuery) }

                queryCache.add(apiQuery)

                inMemoryCache.addAll(
                    shelled
                )
                LoadResult.Page(
                    data = if (position * limit <= body.total) shelled else emptyList(),
                    prevKey = if (position == startPage) null else position - 1,
                    nextKey = if (shelled.isEmpty() || position * limit > body.total) null else position + 1
                )
            }

        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    private fun resultsValidatedAndSorted(albumId: String?): List<TrackShell> {
        return inMemoryCache.filter {
            it.albumId.equals(albumId, false)
        }
    }

    override fun invalidate() {
        super.invalidate()
        inMemoryCache.clear()
        queryCache.clear()
    }
}