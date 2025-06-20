package com.example.online_shope.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.online_shope.Repository.MainRepository
import com.example.online_shope.Activity.Dashboard.CategoryModel
import com.example.online_shope.Item.ItemsModel
import com.example.online_shope.domain.SliderModel

class MainViewModel() : ViewModel() {
    private val repository = MainRepository()

    fun loadBanner(): LiveData<MutableList<SliderModel>> {

        Log.e("TAG", "loadBanner: ${repository.loadBanner().value}")
        return repository.loadBanner()
    }

    fun loadCategory(): LiveData<MutableList<CategoryModel>> {
        return repository.loadCategory()
    }

    fun loadBestSeller(): LiveData<MutableList<ItemsModel>> {
        return repository.loadBestSeller()
    }

    fun loadFiltered(id: String): LiveData<MutableList<ItemsModel>> {
        return repository.loadFiltered(id)
    }
}