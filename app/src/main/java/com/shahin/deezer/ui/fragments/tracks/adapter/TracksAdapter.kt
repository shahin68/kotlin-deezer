package com.shahin.deezer.ui.fragments.tracks.adapter

import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.shahin.deezer.R
import com.shahin.deezer.data.models.tracks.Track
import com.shahin.deezer.databinding.ItemTrackBinding
import com.shahin.deezer.extensions.inflate

class TracksAdapter(
    private val block: (Track) -> Unit
) : PagingDataAdapter<Track, TracksAdapter.TrackViewHolder>(DIFF_COMPARATOR) {

    companion object {
        val DIFF_COMPARATOR = object : DiffUtil.ItemCallback<Track>() {
            override fun areItemsTheSame(oldItem: Track, newItem: Track): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Track, newItem: Track): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class TrackViewHolder(itemView: View, private val block: (Track) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        val binding = ItemTrackBinding.bind(itemView)
        fun bind(item: Track) {
            binding.trackOrderTv.text = bindingAdapterPosition.toString()
            binding.trackTitleTv.text = item.title
            binding.trackArtistTv.text = item.artist.name
            itemView.setOnClickListener {
                block.invoke(item)
            }
        }

        fun clear() {

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        return TrackViewHolder(parent.inflate(R.layout.item_track), block)
    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        } else {
            holder.clear()
        }
    }
}