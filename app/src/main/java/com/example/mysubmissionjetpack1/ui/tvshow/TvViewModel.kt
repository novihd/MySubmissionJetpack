package com.example.mysubmissionjetpack1.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.mysubmissionjetpack1.data.ShowRepository
import com.example.mysubmissionjetpack1.data.source.local.entity.TvEntity
import com.example.mysubmissionjetpack1.vo.Resource

class TvViewModel(private val showRepository: ShowRepository) : ViewModel() {

    fun getSeries(): LiveData<Resource<PagedList<TvEntity>>> = showRepository.getSeries()

}