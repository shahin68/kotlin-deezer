package com.shahin.deezer.ui.fragments.search.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shahin.deezer.R
import com.shahin.deezer.data.models.ArtistModel
import com.shahin.deezer.databinding.ItemArtistBinding
import com.shahin.deezer.extensions.inflate
import com.shahin.deezer.extensions.loadImage

class ArtistsAdapter(
    private val items: List<ArtistModel>,
    private val block: (ArtistModel) -> Unit
) : RecyclerView.Adapter<ArtistsAdapter.ArtistViewHolder>() {


    inner class ArtistViewHolder(itemView: View, private val block: (ArtistModel) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        val binding = ItemArtistBinding.bind(itemView)
        fun bind(item: ArtistModel) {
//            binding.artistIv.loadImage(item.artistImage) // Todo
            binding.artistIv.loadImage(R.drawable.ic_launcher_background)
            binding.artistTv.text = item.artistName
            itemView.setOnClickListener {
                block.invoke(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        return ArtistViewHolder(parent.inflate(R.layout.item_artist), block)
    }

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size
}