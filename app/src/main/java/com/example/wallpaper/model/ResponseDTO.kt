package com.example.wallpaper.model

data class ResponseDTO(
    val next_page: String,
    val page: Int,
    val per_page: Int,
    val photos: List<Photo>,
    val total_results: Int
)