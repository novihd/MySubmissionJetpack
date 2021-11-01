package com.example.mysubmissionjetpack1.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mysubmissionjetpack1.data.ShowRepository
import com.example.mysubmissionjetpack1.di.Injection.provideRepository
import com.example.mysubmissionjetpack1.ui.detail.DetailViewModel
import com.example.mysubmissionjetpack1.ui.favorite.MovieFavViewModel
import com.example.mysubmissionjetpack1.ui.favorite.TvFavViewModel
import com.example.mysubmissionjetpack1.ui.movie.MovieViewModel
import com.example.mysubmissionjetpack1.ui.tvshow.TvViewModel

class ViewModelFactory private constructor(private val showRepository: ShowRepository) : ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
                instance ?: synchronized(this) {
                    instance ?: ViewModelFactory(provideRepository(context))
                }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) ->
                MovieViewModel(showRepository) as T
            modelClass.isAssignableFrom(TvViewModel::class.java) ->
                TvViewModel(showRepository) as T
            modelClass.isAssignableFrom(DetailViewModel::class.java) ->
                DetailViewModel(showRepository) as T
            modelClass.isAssignableFrom(MovieFavViewModel::class.java) ->
                MovieFavViewModel(showRepository) as T
            modelClass.isAssignableFrom(TvFavViewModel::class.java) ->
                TvFavViewModel(showRepository) as T
            else -> throw Throwable("Unknown ViewModel Class: ${modelClass.name}")
        }
    }
}