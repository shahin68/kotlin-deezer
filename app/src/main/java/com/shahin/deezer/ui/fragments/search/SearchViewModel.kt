/*
 * Copyright (c) 2020. By Shahin Montazeri (shahin.montazeri@gmail.com) 
 * Programmed for demonstration purposes
 */

package com.shahin.deezer.ui.fragments.search

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.shahin.deezer.data.models.ResponseData
import com.shahin.deezer.data.models.artist.Artist
import com.shahin.deezer.data.services.ServiceType
import com.shahin.deezer.data.sources.remote.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SearchViewModel @ViewModelInject constructor(
    private val repository: Repository
) : ViewModel() {

    private var currentQueryValue: String? = null

    /**
     * keep Search Results alive in viewmodel until it's changed
     */
    private var currentSearchResult: Flow<PagingData<ResponseData>>? = null

    fun search(queryString: String): Flow<PagingData<ResponseData>> {
        val lastResult = currentSearchResult
        if (queryString == currentQueryValue && lastResult != null) {
            return lastResult
        }
        currentQueryValue = queryString
        val newResult: Flow<PagingData<ResponseData>> =
            repository.fetch(queryString, ServiceType.Artists).map {
                it.insertHeaderItem(
                    ResponseData(queryString, Artist())
                )
            }.cachedIn(viewModelScope)
        currentSearchResult = newResult
        return newResult
    }
}