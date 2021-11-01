package com.example.mysubmissionjetpack1.di

import android.content.Context
import com.example.mysubmissionjetpack1.data.ShowRepository
import com.example.mysubmissionjetpack1.data.source.local.LocalDataSource
import com.example.mysubmissionjetpack1.data.source.local.room.ShowDatabase
import com.example.mysubmissionjetpack1.data.source.remote.RemoteDataSource
import com.example.mysubmissionjetpack1.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): ShowRepository {
        val showDatabase = ShowDatabase.getInstance(context)
        val localDataSource = LocalDataSource.getInstance(showDatabase.showDao())
        val appExecutors = AppExecutors()
        val remoteDataSource = RemoteDataSource.getInstance()
        return ShowRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}