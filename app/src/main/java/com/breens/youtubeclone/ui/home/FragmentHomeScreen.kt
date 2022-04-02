package com.breens.youtubeclone.ui.home

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.breens.youtubeclone.R
import com.breens.youtubeclone.databinding.FragmentHomeScreenBinding
import com.breens.youtubeclone.ui.adapters.PopularVideosAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentHomeScreen : Fragment(R.layout.fragment_home_screen) {

    private var _binding: FragmentHomeScreenBinding? = null
    private val binding get() = _binding!!
    private lateinit var popularVideosAdapter: PopularVideosAdapter
    private val popularVideosViewModel: PopularVideosViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeScreenBinding.inflate(inflater, container, false)
        hideActionBar()
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        observeDataFromYoutubeApi()
        transparentStatusBar()
    }

    private fun setUpRecyclerView() {
        popularVideosAdapter = PopularVideosAdapter()
        binding.videosRecyclerview.apply {
            adapter = popularVideosAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun observeDataFromYoutubeApi() {
        popularVideosViewModel.popularVideos.observe(viewLifecycleOwner) { result ->
            popularVideosAdapter.differ.submitList(result.data?.items)
        }
    }

    @RequiresApi(Build.VERSION_CODES.R)
    @Suppress("DEPRECATION")
    private fun transparentStatusBar() {
        val window = requireActivity().window
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = true
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor =
            requireActivity().resources.getColor(R.color.white)
    }

    private fun hideActionBar() {
        (activity as AppCompatActivity).supportActionBar?.hide()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}