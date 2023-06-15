package com.example.youtube

import android.app.Application
import com.example.youtube.repository.Repository

class App : Application() {


    val repository: Repository by lazy {
        Repository()
    }
}