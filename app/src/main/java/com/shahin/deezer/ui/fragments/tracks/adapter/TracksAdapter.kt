package com.shahin.deezer.ui.fragments.tracks.adapter

import android.view.View
import android.view.ViewGroup
import androidx.annotation.Keep
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.shahin.deezer.R
import com.shahin.deezer.commons.BindingViewHolder
import com.shahin.deezer.commons.StickyHeaderViewHolder
import com.shahin.deezer.data.models.tracks.Track
import com.shahin.deezer.data.models.tracks.TrackShell
import com.shahin.deezer.databinding.ItemTrackBinding
import com.shahin.deezer.extensions.inflate

class TracksAdapter(
    private val block: (Track) -> Unit
) : PagingDataAdapter<TrackShell, BindingViewHolder>(DIFF_COMPARATOR) {

    companion object {
        val DIFF_COMPARATOR = object : DiffUtil.ItemCallback<TrackShell>() {
            override fun areItemsTheSame(oldItem: TrackShell, newItem: TrackShell): Boolean {
                return oldItem.track.id == newItem.track.id
            }

            override fun areContentsTheSame(oldItem: TrackShell, newItem: TrackShell): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class TrackViewHolder(itemView: View, private val block: (Track) -> Unit) : BindingViewHolder(itemView) {
        val binding = ItemTrackBinding.bind(itemView)
        fun bind(item: Track) {
            binding.trackOrderTv.text = bindingAdapterPosition.toString()
            binding.trackTitleTv.text = item.title
            binding.trackArtistTv.text = item.artist?.name ?: ""
            itemView.setOnClickListener {
                block.invoke(item)
            }
        }

        fun clear() {

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {
        return when (viewType) {
            ViewTypes.Track.ordinal -> {
                TrackViewHolder(parent.inflate(R.layout.item_track), block)
            }
            ViewTypes.Header.ordinal -> {
                StickyHeaderViewHolder(parent.inflate(R.layout.header_stikcy))
            }
            else -> throw IllegalArgumentException("viewType $viewType not supported")
        }
    }

    override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
        val item = getItem(position)
        when (holder) {
            is TrackViewHolder -> {
                if (item != null) {
                    holder.bind(item.track)
                } else {
                    holder.clear()
                }
            }
            is StickyHeaderViewHolder -> {
                if (item != null) {
                    holder.bind(item.track.titleVersion, showDivider = true)
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            ViewTypes.Header.ordinal
        } else {
            ViewTypes.Track.ordinal
        }
    }

    @Keep
    enum class ViewTypes {
        Track,
        Header
    }
}