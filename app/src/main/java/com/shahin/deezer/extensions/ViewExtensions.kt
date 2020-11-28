package com.shahin.deezer.extensions

import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.appcompat.widget.AppCompatImageView
import coil.load
import com.shahin.deezer.R

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