package com.shahin.deezer.ui.fragments.search

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shahin.deezer.data.models.ArtistsResponseModel
import com.shahin.deezer.data.sources.artists.ArtistsRepository
import com.shahin.deezer.network.NetworkResponse
import kotlinx.coroutines.launch

class SearchViewModel @ViewModelInject constructor(
    private val artistsRepository: ArtistsRepository
) : ViewModel() {

    private val _artists: MutableLiveData<List<ArtistsResponseModel>> = MutableLiveData()
    val artists: LiveData<List<ArtistsResponseModel>> = _artists

    fun searchForArtist(artistName: String) {
        viewModelScope.launch {
            when (val response = artistsRepository.search(artistName)) {
                is NetworkResponse.Success -> _artists.postValue(response.value)
                is NetworkResponse.GenericError -> {}
                is NetworkResponse.NetworkError -> {}
            }
        }
    }

}