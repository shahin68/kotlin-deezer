/*
 * Copyright (c) 2020. By Shahin Montazeri (shahin.montazeri@gmail.com) 
 * Programmed for demonstration purposes
 */

package com.shahin.deezer.extensions

import android.view.View
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.SearchView
import coil.load
import com.shahin.deezer.R
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import reactivecircus.flowbinding.common.checkMainThread
import reactivecircus.flowbinding.common.safeOffer

/**
 * Extension loading Images using COIL with
 * Network Image Uri
 */
fun AppCompatImageView.loadImage(uri: String?) {
    this.load(uri) {
        placeholder(R.drawable.ic_placeholder)
        crossfade(true)
    }
}

/**
 * Extension loading images using COIL
 * Only for Local Resources
 */
fun AppCompatImageView.loadImage(@DrawableRes resId: Int) {
    this.load(resId) {
        placeholder(R.drawable.ic_placeholder)
        crossfade(true)
    }
}

/**
 * Extension providing Text Watcher to Search View
 * It's used in
 * @see SearchFragment to implement searching flow
 */
@OptIn(ExperimentalCoroutinesApi::class)
fun SearchView.textChanges(): Flow<String> =
    callbackFlow {
        checkMainThread()
        val listener = object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // no need to submit anything
//                safeOffer(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                safeOffer(newText)
                return true
            }
        }
        setOnQueryTextListener(listener)
        awaitClose { setOnQueryTextListener(null) }
    }

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.visibleOrGone(visible: Boolean) {
    if (visible) {
        this.visible()
    } else {
        this.gone()
    }
}