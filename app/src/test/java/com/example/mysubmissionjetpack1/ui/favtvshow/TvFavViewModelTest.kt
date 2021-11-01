package com.example.mysubmissionjetpack1.ui.favtvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.mysubmissionjetpack1.data.ShowRepository
import com.example.mysubmissionjetpack1.data.source.local.entity.TvEntity
import com.example.mysubmissionjetpack1.helper.SortUtil
import com.example.mysubmissionjetpack1.ui.favorite.TvFavViewModel
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvFavViewModelTest {
    private lateinit var viewModel: TvFavViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var showRepository: ShowRepository

    @Mock
    private lateinit var pagedList: PagedList<TvEntity>

    @Mock
    private lateinit var observer: Observer<PagedList<TvEntity>>

    @Before
    fun setUp() {
        viewModel = TvFavViewModel(showRepository)
    }

    @Test
    fun getFavSeries() {
        val dataFavSeries = pagedList
        Mockito.`when`(dataFavSeries.size).thenReturn(2)
        val favSeries = MutableLiveData<PagedList<TvEntity>>()
        favSeries.value = dataFavSeries
        Mockito.`when`(showRepository.getFavSeries(SortUtil.OLDEST)).thenReturn(favSeries)
        val seriesEntity = viewModel.getSeriesFav(SortUtil.OLDEST).value
        verify(showRepository).getFavSeries(SortUtil.OLDEST)
        assertNotNull(seriesEntity)
        assertEquals(2, seriesEntity?.size)
        viewModel.getSeriesFav(SortUtil.OLDEST).observeForever(observer)
        verify(observer).onChanged(dataFavSeries)
    }
}