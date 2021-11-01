package com.example.mysubmissionjetpack1.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.example.mysubmissionjetpack1.data.source.local.entity.MovieEntity
import com.example.mysubmissionjetpack1.data.source.local.entity.TvEntity
import com.example.mysubmissionjetpack1.data.source.local.room.ShowDao
import com.example.mysubmissionjetpack1.helper.SortUtil.getMovieSortQuery
import com.example.mysubmissionjetpack1.helper.SortUtil.getSeriesSortQuery

class LocalDataSource private constructor(private val mShowDao: ShowDao) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(showDao: ShowDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(showDao)
    }

    fun getMovies(): DataSource.Factory<Int, MovieEntity> = mShowDao.getMovies()

    fun getSeries(): DataSource.Factory<Int, TvEntity> = mShowDao.getSeries()


    fun getFavMovies(sort: String): DataSource.Factory<Int, MovieEntity> {
        val query = getMovieSortQuery(sort)
        return mShowDao.getFavMovie(query)
    }

    fun getFavSeries(sort: String): DataSource.Factory<Int, TvEntity> {
        val query = getSeriesSortQuery(sort)
        return mShowDao.getFavSeries(query)
    }

    fun getDetailMovie(movieId: Int): LiveData<MovieEntity> = mShowDao.getDetailMovieById(movieId)

    fun getDetailSeries(series: Int): LiveData<TvEntity> = mShowDao.getDetailSeriesById(series)


    fun insertMovies(movies: List<MovieEntity>) = mShowDao.insertMovies(movies)

    fun insertSeries(series: List<TvEntity>) = mShowDao.insertSeries(series)


    fun insertDetailMovie(detailMovie: MovieEntity) = mShowDao.insertDetailMovie(detailMovie)

    fun insertDetailSeries(detailSeries: TvEntity) = mShowDao.insertDetailSeries(detailSeries)


    fun setFavMovie(movie: MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        mShowDao.updateMovie(movie)
    }

    fun setFavSeries(series: TvEntity, newState: Boolean) {
        series.isFavorite = newState
        mShowDao.updateSeries(series)
    }
}