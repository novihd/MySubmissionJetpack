package com.example.mysubmissionjetpack1.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.mysubmissionjetpack1.data.ShowRepository
import com.example.mysubmissionjetpack1.data.source.local.entity.MovieEntity
import com.example.mysubmissionjetpack1.vo.Resource

class MovieViewModel(private val showRepository: ShowRepository) : ViewModel() {

    fun getMovies(): LiveData<Resource<PagedList<MovieEntity>>> = showRepository.getMovies()

}