package com.example.online_shope.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.online_shope.Repository.MainRepository
import com.example.online_shope.domain.SliderModel

class MainViewModel():ViewModel() {
    private val repository = MainRepository()

    fun loadBanner(): LiveData<MutableList<SliderModel>>{
        return repository.loadBanner()
    }
}