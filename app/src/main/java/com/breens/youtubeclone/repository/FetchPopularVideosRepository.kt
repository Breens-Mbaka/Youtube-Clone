package com.breens.youtubeclone.repository

import com.breens.youtubeclone.data.api.YoutubeApi
import com.breens.youtubeclone.data.models.YoutubeResponse
import retrofit2.Response
import javax.inject.Inject

class FetchPopularVideosRepository @Inject constructor(
    private val api: YoutubeApi
) {

    suspend fun getPopularVideos(): Response<YoutubeResponse> {
        return api.fetchVideos()
    }

}