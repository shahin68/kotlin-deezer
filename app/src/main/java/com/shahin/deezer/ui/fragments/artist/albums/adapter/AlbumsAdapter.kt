package com.shahin.deezer.ui.fragments.artist.albums.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shahin.deezer.R
import com.shahin.deezer.data.models.album.Album
import com.shahin.deezer.databinding.ItemAlbumBinding
import com.shahin.deezer.extensions.inflate
import com.shahin.deezer.extensions.loadImage

class AlbumsAdapter(
    private val items: List<Album>,
    private val block: (Album) -> Unit
) : RecyclerView.Adapter<AlbumsAdapter.AlbumViewHolder>() {


    inner class AlbumViewHolder(itemView: View, private val block: (Album) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        val binding = ItemAlbumBinding.bind(itemView)
        fun bind(item: Album) {
            binding.albumImageIv.loadImage(item.coverMedium)
            binding.albumTitleTv.text = item.title
            binding.albumArtistTv.text = item.artist.name
            itemView.setOnClickListener {
                block.invoke(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        return AlbumViewHolder(parent.inflate(R.layout.item_album), block)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size
}