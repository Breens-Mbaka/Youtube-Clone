package com.breens.youtubeclone.ui.adapters

import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.breens.youtubeclone.data.models.YoutubeVideos
import com.breens.youtubeclone.databinding.ItemVideoBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

class PopularVideosAdapter : RecyclerView.Adapter<PopularVideosAdapter.PopularVideoViewHolder>() {
    inner class PopularVideoViewHolder(val binding: ItemVideoBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val differCallBack = object : DiffUtil.ItemCallback<YoutubeVideos>() {
        override fun areItemsTheSame(oldItem: YoutubeVideos, newItem: YoutubeVideos) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: YoutubeVideos, newItem: YoutubeVideos) =
            oldItem == oldItem
    }

    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularVideoViewHolder {
        val itemPopularVideoBinding = ItemVideoBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return PopularVideoViewHolder(itemPopularVideoBinding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: PopularVideoViewHolder, position: Int) {
        val video = differ.currentList[position]
        val thumbnailUrl = video.snippet.thumbnails.medium.url
        val channelLogo = video.snippet.thumbnails.medium.url
        val title = video.snippet.title
        val channel = video.snippet.channelTitle
        val videoViews = viewsCount(video.statistics.viewCount.toInt())
        val publishedAt = convert(video.snippet.publishedAt)

        holder.binding.apply {
            videoThumbnail.load(thumbnailUrl)
            channelPicture.load(channelLogo)
            videoTitle.text = title
            channelName.text = channel
            views.text = videoViews
            publishedTime.text = publishedAt
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun convert(publishedDay: String): String {
        val formatPattern = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val publishedAt = LocalDateTime.parse(publishedDay, formatPattern)

        val currentDate = LocalDateTime.now().withNano(0)

        val differenceInSeconds = ChronoUnit.SECONDS.between(publishedAt, currentDate)
        val differenceInDays = ChronoUnit.DAYS.between(publishedAt, currentDate)
        val differenceInMonths = ChronoUnit.MONTHS.between(publishedAt, currentDate)
        return findDifference(differenceInSeconds, differenceInDays, differenceInMonths)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun viewsCount(views: Int): String {
        return when {
            views >= 1000000000 -> {
                val formattedViews = views / 1000000
                "${formattedViews}B views"
            }
            views >= 1000000 -> {
                val formattedViews = views / 10000
                "${formattedViews}M views"
            }
            views >= 1000 -> {
                val formattedViews = views / 10000
                "${formattedViews}K views"
            }
            else -> {
                "$views views"
            }
        }
    }

    private fun findDifference(differenceInSeconds: Long, differenceInDays: Long, differenceInMonths:Long): String {
        val hours = differenceInSeconds / 3600
        when(differenceInDays) {
            in 21..31 -> {
                return "3 weeks ago"
            }
            in 14..20 -> {
                return "2 weeks ago"
            }
            in 2..13 -> {
                return "$differenceInDays days ago"
            }
            in 0..1 -> {
                return "$hours hours ago"
            }
        }
        if (differenceInMonths in 0..1) {
            return "$differenceInMonths month ago"
        }
        return "$differenceInMonths months ago"
    }

    override fun getItemCount() = differ.currentList.size
}