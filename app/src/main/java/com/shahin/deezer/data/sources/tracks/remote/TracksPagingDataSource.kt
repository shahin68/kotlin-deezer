package com.shahin.deezer.data.sources.tracks.remote

import androidx.paging.PagingSource
import com.shahin.deezer.data.models.tracks.Track
import com.shahin.deezer.data.services.TracksApi
import retrofit2.HttpException
import java.io.IOException

private const val STARTING_PAGE_INDEX = 1

class TracksPagingDataSource(
    private val service: TracksApi,
    private val albumId: String?
) : PagingSource<Int, Track>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Track> {
        val position = params.key ?: STARTING_PAGE_INDEX
        val apiQuery = albumId
        return try {
            val response = service.queryForTracks(apiQuery, position, params.loadSize)
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