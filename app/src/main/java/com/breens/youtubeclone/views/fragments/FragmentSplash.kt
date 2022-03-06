package com.breens.youtubeclone.views.fragments

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.breens.youtubeclone.R
import com.breens.youtubeclone.databinding.FragmentSplashScreenBinding

class FragmentSplash : Fragment(R.layout.fragment_splash_screen) {
    private var _binding: FragmentSplashScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashScreenBinding.inflate(inflater, container, false)
        hideActionBar()
        navigateToHomeScreen()
        return binding.root
    }

    private fun navigateToHomeScreen() {
        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().navigate(R.id.action_splashScreen_to_fragmentHomeScreen)
        }, 3000)
    }

    private fun hideActionBar() {
        (activity as AppCompatActivity).supportActionBar?.hide()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}