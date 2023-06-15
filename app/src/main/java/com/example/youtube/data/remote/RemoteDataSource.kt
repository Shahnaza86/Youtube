package com.example.youtube.data.remote


import com.example.youtube.core.network.BaseDataSource
import com.example.youtube.core.network.RetrofitClient
import com.example.youtube.core.network.result.Resource
import com.example.youtube.data.remote.model.DetailItem
import com.example.youtube.data.remote.model.Playlist

class RemoteDataSource : BaseDataSource() {
    private val apiService: ApiService by lazy {
        RetrofitClient.create()
    }

    suspend fun getPlaylists(): Resource<Playlist> {
        return getResult {
            apiService.getPlaylists(
                com.example.youtube.BuildConfig.API_KEY,
                "contentDetails,snippet",
                "UCWOA1ZGywLbqmigxE4Qlvuw",
                30
            )
        }
    }

    suspend fun getPlaylistItems(playlistId: String): Resource<DetailItem> {
        return getResult {
            apiService.getPlaylistItems(
                com.example.youtube.BuildConfig.API_KEY,
                "snippet,contentDetails",
                playlistId,
                30
            )
        }
    }
}



