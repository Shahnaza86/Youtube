package com.example.youtube.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.youtube.BuildConfig
import com.example.youtube.core.network.RetrofitClient
import com.example.youtube.core.network.result.Resource
import com.example.youtube.data.remote.ApiService
import com.example.youtube.data.remote.model.DetailItem
import com.example.youtube.data.remote.model.Playlist
import com.example.youtube.di.utils.channelId
import com.example.youtube.di.utils.part
import kotlinx.coroutines.Dispatchers
import retrofit2.Call
import retrofit2.Response

class Repository {
    private  val apiService: ApiService = RetrofitClient.create()

    //    private val dataSource: RemoteDataSource by lazy {
//        RemoteDataSource()
//    }
    fun getPlayList(): LiveData<Resource<Playlist>> {
        val data = MutableLiveData<Resource<Playlist>>()
        data.value = Resource.loading()
        apiService.getPlaylists(
            BuildConfig.API_KEY,
            channelId,
            part
        ).enqueue(object : retrofit2.Callback<Playlist> {
            override fun onResponse(call: Call<Playlist>, response: Response<Playlist>) {
                if (response.isSuccessful) {
                    if (response.isSuccessful) {
                        data.value = Resource.success(response.body())

                    }
                }
            }
            override fun onFailure(call: Call<Playlist>, t: Throwable) {
                data.value = Resource.error(t.message, null, null)
            }
        })
        return data
    }


    fun getPlaylistItems(playlistId: String): LiveData<Resource<DetailItem>> {
        return liveData(Dispatchers.IO) {
            emit(Resource.loading())

//            val response = dataSource.getPlaylistItems(playlistId)
//            emit(response)
        }
    }
}
}