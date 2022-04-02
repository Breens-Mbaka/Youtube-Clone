package com.breens.youtubeclone.data.models

data class VideoDetails(
    val publishedAt: String,
    val title: String,
    val thumbnails: Thumbnails,
    val channelTitle: String
)
