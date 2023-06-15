package com.example.youtube.ui.playlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.youtube.App
import com.example.youtube.BuildConfig
import com.example.youtube.core.ui.base.BaseViewModel
import com.example.youtube.data.remote.model.Playlist
import com.example.youtube.data.remote.ApiService
import com.example.youtube.core.network.RetrofitClient
import com.example.youtube.core.network.result.Resource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlaylistViewModel : BaseViewModel() {
    fun getPlaylists(): LiveData<Resource<Playlist>> {
        return App().repository.getPlaylists()
    }
}

