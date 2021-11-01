package com.example.mysubmissionjetpack1.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mysubmissionjetpack1.R
import com.example.mysubmissionjetpack1.databinding.ActivityFavoriteBinding
import com.example.mysubmissionjetpack1.ui.favorite.SectionPagerAdapterFav

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sectionPagerAdapter =
            SectionPagerAdapterFav.SectionsPagerAdapter(this, supportFragmentManager)
        binding.apply {
            viewPager.adapter = sectionPagerAdapter
            tabs.setupWithViewPager(viewPager)

            tabs.setupWithViewPager(viewPager)
            tabs.getTabAt(0)?.setIcon(R.drawable.ic_baseline_local_movies_24)
            tabs.getTabAt(1)?.setIcon(R.drawable.ic_baseline_live_tv_24)
        }
        supportActionBar?.elevation = 0f

        binding.backButton.setOnClickListener {
            onBackPressed()
        }

    }

}