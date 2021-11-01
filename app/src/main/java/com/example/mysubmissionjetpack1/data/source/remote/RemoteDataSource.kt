package com.example.mysubmissionjetpack1.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mysubmissionjetpack1.data.source.local.room.ApiResponse
import com.example.mysubmissionjetpack1.network.ApiConfig.getApi
import com.example.mysubmissionjetpack1.data.source.remote.response.*
import com.example.mysubmissionjetpack1.utils.EspressoIdlingResource.decrement
import com.example.mysubmissionjetpack1.utils.EspressoIdlingResource.increment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {
    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource = instance ?: synchronized(this) {
            instance ?: RemoteDataSource()
        }

        private val TAG = RemoteDataSource::class.java.simpleName
    }

    fun getMovies(): LiveData<ApiResponse<List<MoviesResult>>> {
        increment()
        val resultMovies = MutableLiveData<ApiResponse<List<MoviesResult>>>()
        val client = getApi().getMovies(1)
        client.enqueue(object : Callback<MoviesResponse> {
            override fun onResponse(
                    call: Call<MoviesResponse>, response: Response<MoviesResponse>
            ) {
                if (response.isSuccessful)
                    resultMovies.postValue(ApiResponse.success(response.body()?.results as List<MoviesResult>))
                else
                    ApiResponse.empty(response.message(), response.body())
                    Log.e(TAG, response.message())
                decrement()
            }

            override fun onFailure(call: Call<MoviesResponse>, t:Throwable) {
                ApiResponse.error("${t.message}", null)
                Log.e(TAG, "${t.message}")
                decrement()
            }
        })
        return resultMovies
    }

    fun getSeries(): LiveData<ApiResponse<List<SeriesResult>>> {
        increment()
        val resultSeries = MutableLiveData<ApiResponse<List<SeriesResult>>>()
        val client = getApi().getSeries(1)
        client.enqueue(object : Callback<SeriesResponse> {
            override fun onResponse(
                call: Call<SeriesResponse>,
                response: Response<SeriesResponse>
            ) {
                if (response.isSuccessful)
                    resultSeries.postValue(response.body()?.let { ApiResponse.success(it.results) })
                else
                    ApiResponse.empty(response.message(), response.errorBody())
                    Log.e(TAG, response.message())
                decrement()
            }

            override fun onFailure(call: Call<SeriesResponse>, t: Throwable) {
                Log.d(TAG, t.message.toString())
                decrement()
            }
        })
        return resultSeries
    }

    fun getDetailMovie(movieId: Int): MutableLiveData<ApiResponse<MovieDetailResponse>> {
        increment()
        val resultDetailMovie = MutableLiveData<ApiResponse<MovieDetailResponse>>()
        val client = getApi().getDetailMovie(movieId)
        client.enqueue(object : Callback<MovieDetailResponse>{
            override fun onResponse(call: Call<MovieDetailResponse>, response: Response<MovieDetailResponse>) {
                if (response.isSuccessful)
                    resultDetailMovie
                        .postValue(ApiResponse.success(response.body() as MovieDetailResponse))
                else
                    ApiResponse.empty(response.message(), response.errorBody())
                    Log.e(TAG, response.message())
                    decrement()
            }

            override fun onFailure(call: Call<MovieDetailResponse>, t: Throwable) {
                ApiResponse.error("${t.message}", null)
                Log.e(TAG, t.message.toString())
                decrement()
            }
        })
        return resultDetailMovie
    }

    fun getDetailSeries(tvId: Int): MutableLiveData<ApiResponse<SeriesDetailResponse>> {
        increment()
        val resultDetailSeries = MutableLiveData<ApiResponse<SeriesDetailResponse>>()
        val client = getApi().getDetailSeries(tvId)
        client.enqueue(object : Callback<SeriesDetailResponse> {
            override fun onResponse(call: Call<SeriesDetailResponse>, response: Response<SeriesDetailResponse>) {
                if (response.isSuccessful)
                    resultDetailSeries.postValue(ApiResponse.success(response.body() as SeriesDetailResponse))
                else
                    ApiResponse.empty(response.message(), response.errorBody())
                    Log.e(TAG, response.message())
                    decrement()
            }

            override fun onFailure(call: Call<SeriesDetailResponse>, t: Throwable) {
                ApiResponse.error(t.message.toString(), null)
                Log.e(TAG, t.message.toString())
                decrement()
            }
        })
        return resultDetailSeries
    }
}

