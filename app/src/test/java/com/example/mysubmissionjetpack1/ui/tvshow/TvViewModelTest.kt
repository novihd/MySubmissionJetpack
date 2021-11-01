package com.example.mysubmissionjetpack1.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.mysubmissionjetpack1.data.ShowRepository
import com.example.mysubmissionjetpack1.data.source.local.entity.TvEntity
import com.example.mysubmissionjetpack1.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvViewModelTest {

    private lateinit var viewModel: TvViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var showRepository: ShowRepository

    @Mock
    private lateinit var pagedList: PagedList<TvEntity>

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<TvEntity>>>

    @Before
    fun setUp() {
        viewModel = TvViewModel(showRepository)
    }

    @Test
    fun getSeries() {
        val dataSeries = Resource.success(pagedList)
        `when`(dataSeries.data?.size).thenReturn(1)
        val series = MutableLiveData<Resource<PagedList<TvEntity>>>()
        series.value = dataSeries
        `when`(showRepository.getSeries()).thenReturn(series)
        val seriesEntity = viewModel.getSeries().value?.data
        verify(showRepository).getSeries()
        assertEquals(1, seriesEntity?.size)
        viewModel.getSeries().observeForever(observer)
        verify(observer).onChanged(dataSeries)
    }
}