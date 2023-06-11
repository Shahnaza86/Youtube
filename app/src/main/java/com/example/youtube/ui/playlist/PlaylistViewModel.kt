package com.example.youtube.ui.playlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.youtube.BuildConfig
import com.example.youtube.core.ui.base.BaseViewModel
import com.example.youtube.data.remote.model.Playlist
import com.example.youtube.data.remote.ApiService
import com.example.youtube.core.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlaylistViewModel : BaseViewModel() {
    private val apiService: ApiService = RetrofitClient.create()
    fun getPlatList(): LiveData<Playlist> {
        return playlists()
    }

    private fun playlists(): LiveData<Playlist> {
        val data = MutableLiveData<Playlist>()

        apiService.getPlaylists(
            BuildConfig.API_KEY,
            "UCWOA1ZGywLbqmigxE4Qlvuw",
            "snippet,contentDetails", 30
        ).enqueue(object : Callback<Playlist> {
            override fun onResponse(call: Call<Playlist>, response: Response<Playlist>) {
                if (response.isSuccessful) {
                    data.value = response.body()
                }
            }

            override fun onFailure(call: Call<Playlist>, t: Throwable) {
                print(t.stackTrace)
                //400 to499
                //404 not found ,401-,400-not correct request
            }
        })
        return data
    }
}