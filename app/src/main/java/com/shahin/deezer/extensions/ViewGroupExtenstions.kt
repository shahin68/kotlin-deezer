/*
 * Copyright (c) 2020. By Shahin Montazeri (shahin.montazeri@gmail.com) 
 * Programmed for demonstration purposes
 */

package com.shahin.deezer.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * View Group extension proving function tp inflate a ViewGroup
 */
fun ViewGroup.inflate(layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}