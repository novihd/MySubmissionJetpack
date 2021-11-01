package com.example.mysubmissionjetpack1.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.mysubmissionjetpack1.data.ShowRepository
import com.example.mysubmissionjetpack1.data.source.local.entity.TvEntity

class TvFavViewModel(private val mShowRepository: ShowRepository): ViewModel() {
    fun getSeriesFav(sort: String): LiveData<PagedList<TvEntity>> =
            mShowRepository.getFavSeries(sort)
}