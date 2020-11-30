package com.shahin.deezer.ui.fragments.search.adapter

import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.shahin.deezer.R
import com.shahin.deezer.data.models.artist.Artist
import com.shahin.deezer.databinding.ItemArtistBinding
import com.shahin.deezer.extensions.inflate
import com.shahin.deezer.extensions.loadImage

class ArtistsAdapter(
    private val block: (Artist) -> Unit
) : PagingDataAdapter<Artist, ArtistsAdapter.ArtistViewHolder>(DIFF_COMPARATOR) {

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

    inner class ArtistViewHolder(itemView: View, private val block: (Artist) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        return ArtistViewHolder(parent.inflate(R.layout.item_artist), block)
    }

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        } else {
            holder.clear()
        }
    }

}