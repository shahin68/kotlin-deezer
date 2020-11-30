package com.shahin.deezer.data.sources.artists.remote

import android.util.Log
import androidx.paging.PagingSource
import com.shahin.deezer.api.ArtistsApi
import com.shahin.deezer.data.models.search.DataItem
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import java.io.IOException

private const val STARTING_PAGE_INDEX = 1

class ArtistsPagingDataSource(
    private val service: ArtistsApi,
    private val query: String
) : PagingSource<Int, DataItem>() {

    companion object {
        private val inMemoryCache = mutableListOf<DataItem>()
        private val queryCache = mutableListOf<String>()
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DataItem> {
        val position = params.key ?: STARTING_PAGE_INDEX
        val apiQuery = query
        return try {
            val lastQuery = queryCache.find { it == apiQuery }
            if (lastQuery == null) {
                val response = service.query(apiQuery, position, params.loadSize)
                val results = response.body()!!
                queryCache.add(apiQuery)
                val lastIds = inMemoryCache.map { it.id }
                inMemoryCache.addAll(
                    results.filter { !lastIds.contains(it.id) }
                )
                val validResults = resultsValidatedAndSorted(query)
                LoadResult.Page(
                    data = validResults,
                    prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                    nextKey = if (validResults.isEmpty()) null else position + 1
                )
            } else {
                val validResults = resultsValidatedAndSorted(query)
                LoadResult.Page(
                    data = validResults,
                    prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                    nextKey = if (validResults.isEmpty()) null else position + 1
                )
            }
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    private fun resultsValidatedAndSorted(query: String): List<DataItem> {
        return inMemoryCache.filter {
            it.artist.name.contains(query, true) ||
                    (it.title.contains(query, true))
        }.sortedWith(compareByDescending<DataItem> { it.artist.name }.thenBy { it.id })
    }

    override fun invalidate() {
        super.invalidate()
        inMemoryCache.clear()
        queryCache.clear()
    }
}