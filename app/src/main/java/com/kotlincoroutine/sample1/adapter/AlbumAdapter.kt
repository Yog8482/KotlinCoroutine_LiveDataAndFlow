package com.kotlincoroutine.sample1.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.kotlincoroutine.sample1.Album
import com.kotlincoroutine.sample1.databinding.AlbumListItemBinding
import com.kotlincoroutine.sample1.ui.AlbumListFragment

class AlbumAdapter constructor(val listner: OnAlbumSelected) :
    ListAdapter<Album, ViewHolder>(AlbumDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return AlbumViewHolder(
            AlbumListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val album = getItem(position)
        (holder as AlbumViewHolder).bind(album)
        holder.itemView.setOnClickListener { listner.OnAlbumSelected(album) }
    }

    class AlbumViewHolder(
        private val binding: AlbumListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Album) {
            binding.apply {
                albumModel = item
                executePendingBindings()
            }
        }
    }

    interface OnAlbumSelected {
        fun OnAlbumSelected(albumModel: Album)
    }
}


private class AlbumDiffCallback : DiffUtil.ItemCallback<Album>() {
    override fun areItemsTheSame(oldItem: Album, newItem: Album): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Album, newItem: Album): Boolean {
        return oldItem == newItem

    }

}