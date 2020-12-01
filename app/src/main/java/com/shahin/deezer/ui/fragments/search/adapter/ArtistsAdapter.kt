/*
 * Copyright (c) 2020. By Shahin Montazeri (shahin.montazeri@gmail.com
 */

package com.shahin.deezer.ui.fragments.search.adapter

import android.view.View
import android.view.ViewGroup
import androidx.annotation.Keep
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.shahin.deezer.R
import com.shahin.deezer.commons.BindingViewHolder
import com.shahin.deezer.commons.StickyHeaderViewHolder
import com.shahin.deezer.data.models.artist.Artist
import com.shahin.deezer.databinding.ItemArtistBinding
import com.shahin.deezer.extensions.inflate
import com.shahin.deezer.extensions.loadImage

class ArtistsAdapter(
    private val block: (Artist) -> Unit
) : PagingDataAdapter<Artist, BindingViewHolder>(DIFF_COMPARATOR) {

    companion object {
        val DIFF_COMPARATOR = object : DiffUtil.ItemCallback<Artist>() {
            override fun areItemsTheSame(oldItem: Artist, newItem: Artist): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Artist, newItem: Artist): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class ArtistViewHolder(itemView: View, private val block: (Artist) -> Unit) : BindingViewHolder(itemView) {
        val binding = ItemArtistBinding.bind(itemView)
        fun bind(item: Artist) {
            binding.artistIv.loadImage(item.pictureMedium)
            binding.artistTv.text = item.name
            itemView.setOnClickListener {
                block.invoke(item)
            }
        }
        fun clear() {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {
        return when (viewType) {
            ViewTypes.Artist.ordinal -> {
                ArtistViewHolder(parent.inflate(R.layout.item_artist), block)
            }
            ViewTypes.Header.ordinal -> {
                StickyHeaderViewHolder(parent.inflate(R.layout.header_stikcy))
            }
            else -> throw IllegalArgumentException("viewType $viewType not supported")
        }
    }

    override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
        when (holder) {
            is ArtistViewHolder -> {
                val item = getItem(position)
                if (item != null) {
                    holder.bind(item)
                } else {
                    holder.clear()
                }
            }
            is StickyHeaderViewHolder -> {
                holder.bind("Artists", showDivider = false)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            ViewTypes.Header.ordinal
        } else {
            ViewTypes.Artist.ordinal
        }
    }

    @Keep
    enum class ViewTypes {
        Artist,
        Header
    }
}