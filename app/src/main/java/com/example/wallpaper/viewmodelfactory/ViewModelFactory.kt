package com.example.wallpaper.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wallpaper.viewmodel.ImageViewModel
import com.example.wallpaper.viewmodel.Image_Repo

class ViewModelFactory(private val imageRepo: Image_Repo):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ImageViewModel(imageRepo) as T
    }
}