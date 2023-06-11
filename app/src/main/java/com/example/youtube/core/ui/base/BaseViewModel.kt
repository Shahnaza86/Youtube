package com.example.youtube.core.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel:ViewModel() {
    val loading=MutableLiveData<Boolean>()
}