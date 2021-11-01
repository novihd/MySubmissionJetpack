package com.example.mysubmissionjetpack1.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.mysubmissionjetpack1.data.source.NetworkBoundResource
import com.example.mysubmissionjetpack1.data.source.local.LocalDataSource
import com.example.mysubmissionjetpack1.data.source.local.entity.MovieEntity
import com.example.mysubmissionjetpack1.data.source.local.entity.TvEntity
import com.example.mysubmissionjetpack1.data.source.local.room.ApiResponse
import com.example.mysubmissionjetpack1.data.source.remote.RemoteDataSource
import com.example.mysubmissionjetpack1.data.source.remote.response.MovieDetailResponse
import com.example.mysubmissionjetpack1.data.source.remote.response.MoviesResult
import com.example.mysubmissionjetpack1.data.source.remote.response.SeriesResult
import com.example.mysubmissionjetpack1.helper.JsonHelper.genreToString
import com.example.mysubmissionjetpack1.helper.JsonHelper.genreTvToString
import com.example.mysubmissionjetpack1.data.source.remote.response.SeriesDetailResponse
import com.example.mysubmissionjetpack1.utils.AppExecutors
import com.example.mysubmissionjetpack1.vo.Resource

class ShowRepository private constructor(
    private val remoteDataSource: RemoteDataSource, private val localDataSource: LocalDataSource, private val appExecutors: AppExecutors) : ShowDataSource{
    companion object {
        @Volatile
        private var instance: ShowRepository? = null

        fun getInstance(remoteDataSource: RemoteDataSource, localDataSource: LocalDataSource, appExecutors: AppExecutors) : ShowRepository =
                instance ?: synchronized(this) {
                    ShowRepository(remoteDataSource, localDataSource, appExecutors).apply {
                        instance = this
                    }
                }
    }

    override fun getMovies(): LiveData<Resource<PagedList<MovieEntity>>> {
        return object : NetworkBoundResource<PagedList<MovieEntity>, List<MoviesResult>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(5)
                    .setPageSize(5)
                    .build()
                return LivePagedListBuilder(localDataSource.getMovies(), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean {
                return data.isNullOrEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<List<MoviesResult>>> {
                return remoteDataSource.getMovies()
            }

            override fun saveCallResult(data: List<MoviesResult>) {
                val movies = ArrayList<MovieEntity>()
                for (response in data) {
                    val movie = with(response) {
                        MovieEntity(id, poster_path, title, "", vote_average, overview, release_date, false)
                    }
                    movies.add(movie)
                }
                Log.d("Movie Response", "$movies")
                localDataSource.insertMovies(movies)
            }
        }.asLiveData()
    }

    override fun getSeries(): LiveData<Resource<PagedList<TvEntity>>> {
        return object : NetworkBoundResource<PagedList<TvEntity>, List<SeriesResult>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<TvEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(5)
                    .setPageSize(5)
                    .build()
                return LivePagedListBuilder(localDataSource.getSeries(), config).build()
            }

            override fun shouldFetch(data: PagedList<TvEntity>?): Boolean {
                return data.isNullOrEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<List<SeriesResult>>> {
                return remoteDataSource.getSeries()
            }

            override fun saveCallResult(data: List<SeriesResult>) {
                val listSeries = ArrayList<TvEntity>()
                for (response in data) {
                    val series = with(response) {
                        TvEntity(id, poster_path, name, "", vote_average, overview, first_air_date, false )
                    }
                    listSeries.add(series)
                }
                Log.d("Series Response", "$listSeries")
                localDataSource.insertSeries(listSeries)
            }
        }.asLiveData()
    }

    override fun getDetailMovie(movieId: Int): LiveData<Resource<MovieEntity>> {
        return object : NetworkBoundResource<MovieEntity, MovieDetailResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<MovieEntity> {
                return localDataSource.getDetailMovie(movieId)
            }

            override fun shouldFetch(data: MovieEntity?): Boolean {
                return data?.genre.isNullOrEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<MovieDetailResponse>> {
                return remoteDataSource.getDetailMovie(movieId)
            }

            override fun saveCallResult(data: MovieDetailResponse) {
                val detailMovie = with(data) {
                    MovieEntity(id, poster_path, title, genreToString(data).toString(), vote_average, overview, release_date, false)
                }
                localDataSource.insertDetailMovie(detailMovie)
            }
        }.asLiveData()
    }

    override fun getDetailTvSeries(tvId: Int): LiveData<Resource<TvEntity>> {
        return object : NetworkBoundResource<TvEntity, SeriesDetailResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<TvEntity> {
                return localDataSource.getDetailSeries(tvId)
            }

            override fun shouldFetch(data: TvEntity?): Boolean {
                return data?.genre.isNullOrEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<SeriesDetailResponse>> {
               return remoteDataSource.getDetailSeries(tvId)
            }

            override fun saveCallResult(data: SeriesDetailResponse) {
                val detailSeries = with(data) {
                    TvEntity(id, poster_path, name, genreTvToString(data).toString(), vote_average, overview, first_air_date, false)
                }
                localDataSource.insertDetailSeries(detailSeries)
            }
        }.asLiveData()
    }

    override fun setFavMovie(movie: MovieEntity, state: Boolean) {
        return appExecutors.diskIO().execute { localDataSource.setFavMovie(movie, state) }
    }

    override fun setFavSeries(series: TvEntity, state: Boolean) {
        return appExecutors.diskIO().execute { localDataSource.setFavSeries(series, state) }
    }

    override fun getFavMovies(sort: String): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setInitialLoadSizeHint(5)
            .setPageSize(5)
            .build()
        return LivePagedListBuilder(localDataSource.getFavMovies(sort), config).build()
    }

    override fun getFavSeries(sort: String): LiveData<PagedList<TvEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setInitialLoadSizeHint(5)
            .setPageSize(5)
            .build()
        return LivePagedListBuilder(localDataSource.getFavSeries(sort), config).build()
    }
}