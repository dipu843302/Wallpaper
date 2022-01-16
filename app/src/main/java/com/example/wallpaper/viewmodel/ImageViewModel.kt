package com.example.wallpaper.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wallpaper.model.ResponseDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ImageViewModel(private val imageRepo: Image_Repo):ViewModel() {

    init{
        viewModelScope.launch(Dispatchers.IO){
            imageRepo.getResponseFromImageApi()
        }
    }
    val images:LiveData<ResponseDTO>
    get() = imageRepo.responseImage
}