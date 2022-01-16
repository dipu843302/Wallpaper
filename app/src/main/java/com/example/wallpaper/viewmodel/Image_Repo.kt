package com.example.wallpaper.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.wallpaper.api.API
import com.example.wallpaper.api.Network
import com.example.wallpaper.model.ResponseDTO

class Image_Repo(private val api:API) {


    private val imageLiveData =MutableLiveData<ResponseDTO>()

    val responseImage:LiveData<ResponseDTO>
    get() = imageLiveData

    suspend fun getResponseFromImageApi(){

        val imageApiResponse=api.getImage("563492ad6f91700001000001294f1f4e2a564bdaa903fa3403cdae53","people")
        if (imageApiResponse!=null){
            imageLiveData.postValue(imageApiResponse.body())
        }
    }
}