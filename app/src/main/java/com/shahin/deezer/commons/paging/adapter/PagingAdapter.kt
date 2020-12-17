/*
 * Copyright (c) 2020. By Shahin Montazeri (shahin.montazeri@gmail.com)
 * Programmed for demonstration purposes
 */

package com.shahin.deezer.commons.paging.adapter

import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

/**
 * Not used at the moment
 */
@Deprecated("Not used yet")
abstract class PagingAdapter<T : Any, VH: RecyclerView.ViewHolder>(diffCallback: DiffUtil.ItemCallback<T>) : PagingDataAdapter<T, VH>(diffCallback) {

}