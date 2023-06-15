package com.example.youtube.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.youtube.core.network.result.Resource
import com.example.youtube.data.remote.RemoteDataSource
import com.example.youtube.data.remote.model.DetailItem
import com.example.youtube.data.remote.model.Playlist
import kotlinx.coroutines.Dispatchers

class Repository {
    private val dataSource: RemoteDataSource by lazy {
        RemoteDataSource()
    }

    fun getPlaylists(): LiveData<Resource<Playlist>> {
        return liveData(Dispatchers.IO) {
            emit(Resource.loading())
            val response = dataSource.getPlaylists()
            emit(response)
        }
    }

    fun getPlaylistItems(playlistId: String): LiveData<Resource<DetailItem>> {
        return liveData(Dispatchers.IO) {
            emit(Resource.loading())
            val response = dataSource.getPlaylistItems(playlistId)
            emit(response)
        }
    }
}