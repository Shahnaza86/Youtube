package com.example.youtube.ui.detail

import androidx.lifecycle.LiveData
import com.example.youtube.core.network.result.Resource
import com.example.youtube.core.ui.base.BaseViewModel
import com.example.youtube.data.remote.model.DetailItem

class DetailViewModel:BaseViewModel() {

    fun getPlaylistItems(playlistId: String): LiveData<Resource<DetailItem>> {
        return repository.getPlaylistItems(playlistId)
    }
}