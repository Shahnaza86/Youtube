package com.example.youtube.ui.playlist.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.youtube.databinding.ItemPlaylistBinding
import com.example.youtube.data.remote.model.Playlist
import com.example.youtube.di.utils.loadImage

class PlaylistAdapter(private val onClick: (Playlist.Item) -> Unit) :
    Adapter<PlaylistAdapter.PlaylistViewHolder>() {

    private var list = ArrayList<Playlist.Item>()

    @SuppressLint("NotifyDataSetChanged")
    fun addList(list: List<Playlist.Item>) {
        this.list = list as ArrayList<Playlist.Item>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistViewHolder {
        return PlaylistViewHolder(
            ItemPlaylistBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: PlaylistViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class PlaylistViewHolder(
        private val binding: ItemPlaylistBinding
    ) : ViewHolder(binding.root) {
        fun bind(item: Playlist.Item) {
            with(binding) {
                tvPlaylistName.text = item.snippet?.title
                tvNumberOfVideos.text = item.contentDetails?.itemCount.toString() + "video series"
                ivPlaylist.loadImage(item.snippet?.thumbnails?.default?.url!!)
                cvPlaylist.setOnClickListener {
                    onClick.invoke(item)
                }
            }

        }
    }
}