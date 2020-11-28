package com.shahin.deezer.ui.fragments.artist.tracks.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shahin.deezer.R
import com.shahin.deezer.data.models.TrackModel
import com.shahin.deezer.databinding.ItemTrackBinding
import com.shahin.deezer.extensions.inflate

class TracksAdapter(
    private val items: List<TrackModel>,
    private val block: (TrackModel) -> Unit
) : RecyclerView.Adapter<TracksAdapter.TrackViewHolder>() {


    inner class TrackViewHolder(itemView: View, private val block: (TrackModel) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        val binding = ItemTrackBinding.bind(itemView)
        fun bind(item: TrackModel) {
            binding.trackOrderTv.text = adapterPosition.toString()
            binding.trackTitleTv.text = item.trackTitle
            binding.trackArtistTv.text = item.trackArtistName
            itemView.setOnClickListener {
                block.invoke(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        return TrackViewHolder(parent.inflate(R.layout.item_track), block)
    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size
}