package com.shahin.deezer.data.sources.localsource

import com.shahin.deezer.BuildConfig
import javax.inject.Inject

class LocalDataSourceImp @Inject constructor(): LocalDataSource {
    override fun getApiKey(): String {
        return BuildConfig.SECRET
    }

}