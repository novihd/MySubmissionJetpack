package com.example.mysubmissionjetpack1.network

import com.example.mysubmissionjetpack1.data.source.remote.response.MovieDetailResponse
import com.example.mysubmissionjetpack1.data.source.remote.response.MoviesResponse
import com.example.mysubmissionjetpack1.data.source.remote.response.SeriesDetailResponse
import com.example.mysubmissionjetpack1.data.source.remote.response.SeriesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    companion object {
        const val API_KEY = "18b9e5d5602de2a8e3a2d98d738bd715"
    }

    @GET("discover/movie?api_key=$API_KEY")
    fun getMovies (
        @Query("page") position: Int
    ) : Call<MoviesResponse>

    @GET("discover/tv?api_key=$API_KEY")
    fun getSeries(
        @Query("page") position: Int
    ) : Call<SeriesResponse>

    @GET("movie/{movie_id}?api_key=$API_KEY")
    fun getDetailMovie(
        @Path("movie_id") position: Int
    ) : Call<MovieDetailResponse>

    @GET("tv/{tv_id}?api_key=$API_KEY")
    fun getDetailSeries(
            @Path("tv_id") position: Int
    ) : Call<SeriesDetailResponse>

}