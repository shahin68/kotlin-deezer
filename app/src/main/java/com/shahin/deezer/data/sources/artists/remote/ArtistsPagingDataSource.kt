package com.shahin.deezer.data.sources.artists.remote

import androidx.paging.PagingSource
import com.shahin.deezer.api.ArtistsApi
import com.shahin.deezer.data.models.search.DataItem
import retrofit2.HttpException
import java.io.IOException

private const val STARTING_PAGE_INDEX = 1

class ArtistsPagingDataSource(
    val service: ArtistsApi,
    val query: String
) : PagingSource<Int, DataItem>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DataItem> {
        val position = params.key ?: STARTING_PAGE_INDEX
        val apiQuery = query
        return try {
            val response = service.query(apiQuery, position, params.loadSize)
            val repos = response.body()!!
            LoadResult.Page(
                data = repos,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (repos.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}