package com.breens.youtubeclone.util

class Constants {
    companion object {
        const val BASE_URL = "https://youtube.googleapis.com/youtube/v3/"

        //This endpoint gets a list of videos
        const val LIST_OF_VIDEOS = "videos"

        //This endpoint gets the channel info e.g channel logo, subscribers count
        const val CHANNEL_INFO = "channels"

        //PART PROPERTIES
        const val SNIPPET = "snippet"
        const val DETAILS = "contentDetails"
        const val STATISTICS = "statistics"

        //CHART PROPERTY
        const val MOST_POPULAR = "mostPopular"

        //REGION_CODE
        const val REGION_CODE = "US"

        //PUT YOUR OWN GOOGLE API KEY👇
        const val KEY = ""
    }
}