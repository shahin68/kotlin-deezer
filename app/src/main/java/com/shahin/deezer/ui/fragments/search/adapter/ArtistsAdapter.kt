package com.shahin.deezer.ui.fragments.search.adapter

import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.shahin.deezer.R
import com.shahin.deezer.data.models.search.DataItem
import com.shahin.deezer.databinding.ItemArtistBinding
import com.shahin.deezer.extensions.inflate
import com.shahin.deezer.extensions.loadImage

class ArtistsAdapter(
    private val block: (DataItem) -> Unit
) : PagingDataAdapter<DataItem, ArtistsAdapter.ArtistViewHolder>(DIFF_COMPARATOR) {

    companion object {
        val DIFF_COMPARATOR = object : DiffUtil.ItemCallback<DataItem>() {
            override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class ArtistViewHolder(itemView: View, private val block: (DataItem) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        val binding = ItemArtistBinding.bind(itemView)
        fun bind(item: DataItem?) {
            item?.let {
                binding.artistIv.loadImage(item.artist.pictureMedium)
                binding.artistTv.text = item.artist.name
                itemView.setOnClickListener {
                    block.invoke(item)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        return ArtistViewHolder(parent.inflate(R.layout.item_artist), block)
    }

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}