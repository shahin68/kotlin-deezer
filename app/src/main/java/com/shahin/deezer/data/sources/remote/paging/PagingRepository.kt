/*
 * Copyright (c) 2020. By Shahin Montazeri (shahin.montazeri@gmail.com)
 * Programmed for demonstration purposes
 */

package com.shahin.deezer.data.sources.remote.paging

import androidx.paging.PagingSource
import com.shahin.deezer.data.models.ResponseData
import com.shahin.deezer.data.models.album.Album
import com.shahin.deezer.data.models.artist.Artist
import com.shahin.deezer.data.models.tracks.Track
import com.shahin.deezer.data.services.ServiceType
import retrofit2.HttpException
import java.io.IOException

class PagingRepository(
    private val query: String,
    private val serviceType: ServiceType,
    private val pagingDataSource: PagingDataSource
) : PagingSource<Int, ResponseData>() {

    companion object {
        private val inMemoryCache =
            mutableListOf<ResponseData>() // (*) we don't care about the object type
        private val queryCache = mutableListOf<String>()
    }

    private val startPage = 0
    private val limit = 25

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResponseData> {
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
                val response = when (serviceType) {
                    ServiceType.Artists -> pagingDataSource.fetchArtist(apiQuery, position * limit, limit)
                    ServiceType.Albums -> pagingDataSource.fetchAlbum(apiQuery, position * limit, limit)
                    ServiceType.Tracks -> pagingDataSource.fetchTrack(apiQuery, position * limit, limit)
                }
                val body = response.body()!!
                val results = response.body()!!.data

                queryCache.add(apiQuery)

                val shelled = results.map { ResponseData(query = apiQuery, data = it) }

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

    private fun resultsValidatedAndSorted(query: String): List<ResponseData> {
        return inMemoryCache.filter {
            it.query.contains(query, true)
        }
    }

    override fun invalidate() {
        super.invalidate()
        inMemoryCache.clear()
        queryCache.clear()
    }
}