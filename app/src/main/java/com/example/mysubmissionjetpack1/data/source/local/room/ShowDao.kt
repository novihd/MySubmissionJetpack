package com.example.mysubmissionjetpack1.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.example.mysubmissionjetpack1.data.source.local.entity.MovieEntity
import com.example.mysubmissionjetpack1.data.source.local.entity.TvEntity

@Dao
interface ShowDao {

    @Query("SELECT * FROM movie_entity ORDER BY date DESC")
    fun getMovies(): DataSource.Factory<Int, MovieEntity>

    @RawQuery(observedEntities = [MovieEntity::class])
    fun getFavMovie(query: SupportSQLiteQuery): DataSource.Factory<Int, MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movie: List<MovieEntity>)

    @Update
    fun updateMovie(movie: MovieEntity)

    @Query("SELECT * FROM movie_entity WHERE showId = :id")
    fun getDetailMovieById(id: Int): LiveData<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDetailMovie(detailMovie: MovieEntity)




    @Query("SELECT * FROM tv_entity ORDER BY date DESC")
    fun getSeries(): DataSource.Factory<Int, TvEntity>

    @RawQuery(observedEntities = [TvEntity::class])
    fun getFavSeries(query: SupportSQLiteQuery): DataSource.Factory<Int, TvEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSeries(series: List<TvEntity>)

    @Update
    fun updateSeries(series: TvEntity)

    @Query("SELECT * FROM tv_entity WHERE showId = :id")
    fun getDetailSeriesById(id: Int): LiveData<TvEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDetailSeries(detailSeries: TvEntity)
}