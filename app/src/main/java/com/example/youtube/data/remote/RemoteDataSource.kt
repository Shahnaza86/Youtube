package com.example.youtube.data.remote

import androidx.viewbinding.BuildConfig
import com.example.youtube.BuildConfig
import com.example.youtube.core.network.RetrofitClient
import com.example.youtube.core.network.result.Resource
import com.example.youtube.data.remote.model.Playlist

class RemoteDataSource : BaseDataSource() {
    private val apiService: ApiService by lazy {
        RetrofitClient.create()
    }


    suspend fun getPlayLists(): Resource<Playlists> {
        return getResult {
            apiService.getPlaylists(
                BuildConfig.API_KEY,
                "UCWOA1ZGywLbqmigxE4Qlvuw",
                "snippet,contentDetails",
                50)

        }
    }
    suspend fun getItemList(id: String) : Resource<PlaylistItem>{
        return getResult {
            apiService.getItemLists(
                BuildConfig.API_KEY,
                "snippet,contentDetails",
                50,
                id
            )
        }
    }
    suspend fun getVideoPlayer(id: String): Resource<Playlists>{
        return getResult {
            apiService.getVideo(
                BuildConfig.API_KEY,
                "snippet,contentDetails",
                id

            )
        }
    }
}
