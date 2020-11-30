package com.shahin.deezer.extensions

import android.text.Editable
import android.text.TextWatcher
import android.util.DisplayMetrics
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.shahin.deezer.R
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import reactivecircus.flowbinding.common.checkMainThread
import reactivecircus.flowbinding.common.safeOffer

fun AppCompatImageView.loadImage(uri: String) {
    this.load(uri) {
        placeholder(R.drawable.ic_placeholder)
        crossfade(true)
    }
}

fun AppCompatImageView.loadImage(@DrawableRes resId: Int) {
    this.load(resId) {
        placeholder(R.drawable.ic_placeholder)
        crossfade(true)
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
fun SearchView.textChanges(): Flow<String> =
    callbackFlow {
        checkMainThread()
        val listener = object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
//                safeOffer(query) // no need to submit anything
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

fun RecyclerView.fastScrollUp() {
    val milliconds = 16
    val smoothScroller = object : LinearSmoothScroller(context) {
        override fun getVerticalSnapPreference(): Int {
            return SNAP_TO_START
        }

        override fun calculateSpeedPerPixel(displayMetrics: DisplayMetrics?): Float {
            return milliconds.toFloat() / (displayMetrics?.densityDpi ?: milliconds)
        }
    }

    smoothScroller.targetPosition = 0
    layoutManager?.startSmoothScroll(smoothScroller)
}