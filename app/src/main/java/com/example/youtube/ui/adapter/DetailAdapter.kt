package com.example.youtube.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.youtube.data.remote.model.DetailItem
import com.example.youtube.data.remote.model.Playlist
import com.example.youtube.databinding.ItemDetailBinding
import com.example.youtube.di.utils.loadImage

class DetailAdapter():  RecyclerView.Adapter<DetailAdapter.DetailViewHolder>() {

    private var list = ArrayList<DetailItem.Item>()
    @SuppressLint("NotifyDataSetChanged")
    fun addList(list: List<Playlist.Item>) {
        this.list = list as ArrayList<DetailItem.Item>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        return DetailViewHolder(ItemDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class DetailViewHolder(private val binding: ItemDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DetailItem.Item) {
            with(binding) {
                tvDesc.text = item.snippet?.title
                timeOfVideo.text = item.snippet?.publishedAt
                imgSelectVideo.loadImage(item.snippet?.thumbnails?.standard?.url!!)
            }
        }
    }
}