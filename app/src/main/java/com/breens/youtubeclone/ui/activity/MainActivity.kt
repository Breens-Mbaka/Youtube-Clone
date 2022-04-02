package com.breens.youtubeclone.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import com.breens.youtubeclone.R
import com.breens.youtubeclone.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpNav()
    }

    private fun setUpNav() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as
                NavHostFragment
        val navController = navHostFragment.navController

        navController.addOnDestinationChangedListener{_, destination, _ ->
            when(destination.id) {
                R.id.splashScreen -> hideBottomNavigation()
                else -> showBottomNavigation()
            }
        }
    }

    private fun showBottomNavigation() {
        binding.bottomNavigation.visibility = View.VISIBLE
    }

    private fun hideBottomNavigation() {
        binding.bottomNavigation.visibility = View.GONE
    }
}