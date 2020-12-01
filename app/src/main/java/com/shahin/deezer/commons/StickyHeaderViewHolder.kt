/*
 * Copyright (c) 2020. By Shahin Montazeri (shahin.montazeri@gmail.com
 */

package com.shahin.deezer.commons

import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.isVisible
import com.shahin.deezer.R
import com.shahin.deezer.extensions.visibleOrGone

class StickyHeaderViewHolder(itemView: View) : BindingViewHolder(itemView) {
    private val container = itemView.findViewById<LinearLayout>(R.id.header_container)
    private val tv = itemView.findViewById<TextView>(R.id.header_title)
    private val divider = itemView.findViewById<View>(R.id.header_divider)
    fun bind(title: String?, showDivider: Boolean) {
        tv.text = title ?: itemView.context.getString(R.string.tracks_list_placeholder_header_album_volume)
        divider.isVisible = showDivider
    }
}
