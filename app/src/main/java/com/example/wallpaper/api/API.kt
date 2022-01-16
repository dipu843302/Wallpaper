package com.example.wallpaper.api

import com.example.wallpaper.model.ResponseDTO
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface API {

    @GET("v1/search")
    suspend fun getImage(@Header("Authorization")token:String,
                         @Query("query")query: String) :Response<ResponseDTO>
}