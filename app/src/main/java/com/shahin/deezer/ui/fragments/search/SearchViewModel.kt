package com.shahin.deezer.ui.fragments.search

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.PagedList
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.shahin.deezer.data.models.search.DataItem
import com.shahin.deezer.data.sources.artists.ArtistsRepository
import com.shahin.deezer.network.NetworkResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class SearchViewModel @ViewModelInject constructor(
    private val artistsRepository: ArtistsRepository
) : ViewModel() {

    private var currentQueryValue: String? = null

    private var currentSearchResult: Flow<PagingData<DataItem>>? = null

    fun search(queryString: String): Flow<PagingData<DataItem>> {
        val lastResult = currentSearchResult
        if (queryString == currentQueryValue && lastResult != null) {
            return lastResult
        }
        currentQueryValue = queryString
        val newResult: Flow<PagingData<DataItem>> = artistsRepository.search(queryString)
            .cachedIn(viewModelScope)
        currentSearchResult = newResult
        return newResult
    }
}