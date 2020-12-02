/*
 * Copyright (c) 2020. By Shahin Montazeri (shahin.montazeri@gmail.com) 
 * Programmed for demonstration purposes
 */

package com.shahin.deezer.data.sources.localsource

import com.shahin.deezer.BuildConfig
import javax.inject.Inject

/**
 * Main Local Repository
 * App Local Repository
 * If we need a general parameter, we use Local Repo
 * We may also never use this repository
 */
class LocalDataSourceImp @Inject constructor(): LocalDataSource {
    override fun getApiKey(): String {
        return BuildConfig.SECRET
    }

}