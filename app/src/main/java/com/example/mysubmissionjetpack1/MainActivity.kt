package com.example.mysubmissionjetpack1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mysubmissionjetpack1.databinding.ActivityMainBinding
import com.example.mysubmissionjetpack1.ui.FavoriteActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sectionPagerAdapter =
            SectionPagerAdapter.SectionsPagerAdapter(this, supportFragmentManager)
        binding.apply {
            viewPager.adapter = sectionPagerAdapter
            tabs.setupWithViewPager(viewPager)

            tabs.setupWithViewPager(viewPager)
            tabs.getTabAt(0)?.setIcon(R.drawable.ic_baseline_local_movies_24)
            tabs.getTabAt(1)?.setIcon(R.drawable.ic_baseline_live_tv_24)
        }

        binding.favorite.setOnClickListener {
            val intent = Intent(this, FavoriteActivity::class.java)
            startActivity(intent)
        }

        supportActionBar?.elevation = 0f



    }
}