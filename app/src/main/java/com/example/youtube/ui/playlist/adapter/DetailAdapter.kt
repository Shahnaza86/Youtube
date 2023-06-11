package com.example.youtube.ui.playlist.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.youtube.data.remote.model.DetailItem
import com.example.youtube.data.remote.model.Playlist

class DetailAdapter(): : Adapter<DetailAdapter.DetailViewHolder>() {

    private var list = ArrayList<DetailItem.Item>()


    fun addList(list: List<Playlist.Item>) {
        this.list = list as ArrayList<DetailItem.Item>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        return DetailViewHolder(
            ItemDetailsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    inner class DetailViewHolder(private val binding: ItemDetailsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DetailItem.Item) {
            with(binding) {
                tvVideoName.text = item.snippet?.title
                tvTime.text = item.snippet?.publishedAt
                ivVideo.loadImage(item.snippet?.thumbnails?.standard?.url!!)
            }
        }
    }
} {
}