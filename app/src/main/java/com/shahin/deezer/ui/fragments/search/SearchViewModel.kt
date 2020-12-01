package com.shahin.deezer.ui.fragments.search

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.shahin.deezer.data.models.artist.Artist
import com.shahin.deezer.data.models.artist.ArtistShell
import com.shahin.deezer.data.sources.artists.ArtistsRepository
import kotlinx.coroutines.flow.Flow

class SearchViewModel @ViewModelInject constructor(
    private val artistsRepository: ArtistsRepository
) : ViewModel() {

    private var currentQueryValue: String? = null

    private var currentSearchResult: Flow<PagingData<Artist>>? = null

    fun search(queryString: String): Flow<PagingData<Artist>> {
        val lastResult = currentSearchResult
        if (queryString == currentQueryValue && lastResult != null) {
            return lastResult
        }
        currentQueryValue = queryString
        val newResult: Flow<PagingData<Artist>> = artistsRepository.search(queryString)
            .cachedIn(viewModelScope)
        currentSearchResult = newResult
        return newResult
    }
}