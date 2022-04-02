package com.breens.youtubeclone.data.models

data class YoutubeVideos(
    val id: String,
    val snippet: VideoDetails,
    val contentDetails: ContentDetails,
    val statistics: VideoStats
)
