package com.example.mysubmissionjetpack1.ui.favorite

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.mysubmissionjetpack1.R

class SectionPagerAdapterFav {
    class SectionsPagerAdapter(private val mContext: Context, fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        companion object {
            @StringRes
            private val TAB_TITLES = intArrayOf(R.string.movie, R.string.tv)
        }

        override fun getCount(): Int {
            return TAB_TITLES.size
        }

        override fun getItem(position: Int): Fragment =
            when (position) {
                0 -> MovieFavFragment()
                1 -> TvFavFragment()
                else -> Fragment()
            }

        override fun getPageTitle(position: Int): CharSequence {
            return mContext.resources.getString(TAB_TITLES[position])
        }
    }
}