package com.shahin.deezer.ui.fragments.albums.adapter

import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.shahin.deezer.R
import com.shahin.deezer.data.models.album.Album
import com.shahin.deezer.databinding.ItemAlbumBinding
import com.shahin.deezer.extensions.inflate
import com.shahin.deezer.extensions.loadImage

class AlbumsAdapter(
    private val artistName: String?,
    private val block: (Album) -> Unit
) : PagingDataAdapter<Album, AlbumsAdapter.AlbumViewHolder>(DIFF_COMPARATOR) {

    companion object {
        val DIFF_COMPARATOR = object : DiffUtil.ItemCallback<Album>() {
            override fun areItemsTheSame(oldItem: Album, newItem: Album): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Album, newItem: Album): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class AlbumViewHolder(itemView: View, private val block: (Album) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        val binding = ItemAlbumBinding.bind(itemView)
        fun bind(item: Album) {
            binding.albumImageIv.loadImage(item.coverMedium)
            binding.albumTitleTv.text = item.title
            binding.albumArtistTv.text = item.artist?.name ?: artistName ?: ""
            itemView.setOnClickListener {
                block.invoke(item)
            }
        }
        fun clear() {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        return AlbumViewHolder(parent.inflate(R.layout.item_album), block)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        } else {
            holder.clear()
        }
    }

}