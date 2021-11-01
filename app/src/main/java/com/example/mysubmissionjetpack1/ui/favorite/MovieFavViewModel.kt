package com.example.mysubmissionjetpack1.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.mysubmissionjetpack1.data.ShowRepository
import com.example.mysubmissionjetpack1.data.source.local.entity.MovieEntity

class MovieFavViewModel(private val mShowRepository: ShowRepository): ViewModel() {
    fun getMovieFav(sort: String): LiveData<PagedList<MovieEntity>> =
            mShowRepository.getFavMovies(sort)

}