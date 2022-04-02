package com.breens.youtubeclone.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.breens.youtubeclone.data.models.YoutubeResponse
import com.breens.youtubeclone.repository.FetchPopularVideosRepository
import com.breens.youtubeclone.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class PopularVideosViewModel @Inject constructor(
    private val repository: FetchPopularVideosRepository
): ViewModel() {

    private val _popularVideos: MutableLiveData<Resource<YoutubeResponse>> = MutableLiveData()
    var popularVideos: LiveData<Resource<YoutubeResponse>> = _popularVideos

    init {
        fetchPopularVideos()
    }

    private fun fetchPopularVideos() = viewModelScope.launch {
        _popularVideos.postValue(Resource.Loading())
        val response = repository.getPopularVideos()
        _popularVideos.postValue(handleYoutubeResponse(response))
    }

    private fun handleYoutubeResponse(response: Response<YoutubeResponse>): Resource<YoutubeResponse> {
        if (response.isSuccessful) {
            response.body()?.let { youtubeResponse ->
                return Resource.Success(youtubeResponse)
            }
        }
        return Resource.Error(response.message())
    }
}