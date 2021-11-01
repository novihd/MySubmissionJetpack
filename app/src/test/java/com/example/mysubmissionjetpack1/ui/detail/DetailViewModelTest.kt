package com.example.mysubmissionjetpack1.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.mysubmissionjetpack1.data.ShowRepository
import com.example.mysubmissionjetpack1.data.source.local.entity.MovieEntity
import com.example.mysubmissionjetpack1.data.source.local.entity.TvEntity
import com.example.mysubmissionjetpack1.ui.detail.DetailViewModel.Companion.MOVIE
import com.example.mysubmissionjetpack1.ui.detail.DetailViewModel.Companion.TV_SERIES
import com.example.mysubmissionjetpack1.utils.DataShow
import com.example.mysubmissionjetpack1.vo.Resource
import com.nhaarman.mockitokotlin2.doNothing
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertNotNull
import org.junit.Test
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verifyNoMoreInteractions
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel
    private val dataMovies = DataShow.getMovieDetail()[0]
    private val moviesId = dataMovies.showId
    private val dataSeries = DataShow.getSeriesDetail()[0]
    private val seriesId = dataSeries.showId

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var showRepository: ShowRepository

    @Mock
    private lateinit var detailMovieObserver: Observer<Resource<MovieEntity>>

    @Mock
    private lateinit var detailSeriesObserver: Observer<Resource<TvEntity>>

    @Before
    fun setUp() {
        viewModel = DetailViewModel(showRepository)

    }

    @Test
    fun getMovieDetail() {
        viewModel.setSelectedShow(moviesId, MOVIE)
        val detailMovie = Resource.success(dataMovies)
        val detailMovies = MutableLiveData<Resource<MovieEntity>>()
        detailMovies.value = detailMovie
        `when`(showRepository.getDetailMovie(moviesId)).thenReturn(detailMovies)
        viewModel.detailMovie.observeForever(detailMovieObserver)
        assertNotNull(detailMovie.data)
        verify(detailMovieObserver).onChanged(detailMovie)
    }

    @Test
    fun getSeriesDetail() {
        viewModel.setSelectedShow(seriesId, TV_SERIES)
        val detailSeries = Resource.success(dataSeries)
        val detailTvSeries = MutableLiveData<Resource<TvEntity>>()
        detailTvSeries.value = detailSeries
        `when`(showRepository.getDetailTvSeries(seriesId)).thenReturn(detailTvSeries)
        viewModel.detailSeries.observeForever(detailSeriesObserver)
        assertNotNull(detailSeries.data)
        verify(detailSeriesObserver).onChanged(detailSeries)
    }

    @Test
    fun setFavMovie() {
        val detailMovie = Resource.success(dataMovies)
        val detailMovies = MutableLiveData<Resource<MovieEntity>>()
        detailMovies.value = detailMovie
        viewModel.detailMovie = detailMovies
        detailMovie.data ?.let { doNothing().`when`(showRepository).setFavMovie(it,true) }
        viewModel.setFavMovies()
        verify(showRepository).setFavMovie(detailMovies.value?.data as MovieEntity, true)
        verifyNoMoreInteractions(detailMovieObserver)
    }

    @Test
    fun setFavSeries() {
        val detailSeries = Resource.success(dataSeries)
        val detailTvSeries = MutableLiveData<Resource<TvEntity>>()
        detailTvSeries.value = detailSeries
        viewModel.detailSeries = detailTvSeries
        detailSeries.data ?.let { doNothing().`when`(showRepository).setFavSeries(it,true) }
        viewModel.setFavSeries()
        verify(showRepository).setFavSeries(detailTvSeries.value?.data as TvEntity, true)
        verifyNoMoreInteractions(detailSeriesObserver)
    }

}