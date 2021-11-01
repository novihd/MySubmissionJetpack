package com.example.mysubmissionjetpack1.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.mysubmissionjetpack1.data.ShowRepository
import com.example.mysubmissionjetpack1.data.source.local.entity.MovieEntity
import com.example.mysubmissionjetpack1.data.source.local.entity.TvEntity
import com.example.mysubmissionjetpack1.vo.Resource

class DetailViewModel(private val showRepository: ShowRepository) : ViewModel() {

    companion object {
        const val MOVIE = "movie"
        const val TV_SERIES = "tv_series"
    }

    private var movieId = MutableLiveData<Int>()
    private var seriesId = MutableLiveData<Int>()

    fun setSelectedShow(id: Int, category: String) {
        when (category) {
            MOVIE -> {
                this.movieId.value = id
            }
            TV_SERIES -> {
                this.seriesId.value = id
            }
        }
    }

    var detailMovie: LiveData<Resource<MovieEntity>> = Transformations.switchMap(movieId) {
        showRepository.getDetailMovie(it)
    }

    var detailSeries: LiveData<Resource<TvEntity>> = Transformations.switchMap(seriesId) {
        showRepository.getDetailTvSeries(it)
    }

    fun setFavMovies() {
        val detailMovieRes = detailMovie.value
        if (detailMovieRes != null) {
            val detailMovie = detailMovieRes.data
            if (detailMovie != null) {
                val newState = !detailMovie.isFavorite
                showRepository.setFavMovie(detailMovie, newState)
            }
        }
    }

    fun setFavSeries() {
        val detailSeriesRes = detailSeries.value
        if (detailSeriesRes != null) {
            val detailSeries = detailSeriesRes.data
            if (detailSeries != null) {
                val newState = !detailSeries.isFavorite
                showRepository.setFavSeries(detailSeries, newState)
            }
        }
    }

}