package com.shahin.deezer.data.sources.localsource

import javax.inject.Inject

class LocalDataSourceImp @Inject constructor(): LocalDataSource {
    override fun getApiKey(): String {
        return "AIzaSyBDpBAz9F1TNhu9lJ8cwxprEQXs7Fbtqqw"
    }

}