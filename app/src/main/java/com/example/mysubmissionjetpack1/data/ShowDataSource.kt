package com.example.mysubmissionjetpack1.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.mysubmissionjetpack1.data.source.local.entity.MovieEntity
import com.example.mysubmissionjetpack1.data.source.local.entity.TvEntity
import com.example.mysubmissionjetpack1.vo.Resource

interface ShowDataSource {
    fun getMovies(): LiveData<Resource<PagedList<MovieEntity>>>

    fun getSeries(): LiveData<Resource<PagedList<TvEntity>>>

    fun getDetailMovie(movieId: Int): LiveData<Resource<MovieEntity>>

    fun getDetailTvSeries(tvId: Int): LiveData<Resource<TvEntity>>

    fun setFavMovie(movie: MovieEntity, state: Boolean)

    fun setFavSeries(series: TvEntity, state: Boolean)

    fun getFavMovies(sort: String): LiveData<PagedList<MovieEntity>>

    fun getFavSeries(sort: String): LiveData<PagedList<TvEntity>>
}