package com.example.mysubmissionjetpack1.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.mysubmissionjetpack1.data.ShowRepository
import com.example.mysubmissionjetpack1.data.source.local.entity.MovieEntity
import com.example.mysubmissionjetpack1.vo.Resource
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var showRepository: ShowRepository

    @Mock
    private lateinit var pagedList: PagedList<MovieEntity>

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<MovieEntity>>>


    @Before
    fun setUp() {
        viewModel = MovieViewModel(showRepository)
    }

    @Test
    fun getMovies() {
        val dataMovie = Resource.success(pagedList)
        `when`(dataMovie.data?.size).thenReturn(3)
        val movie = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        movie.value = dataMovie
        `when`(showRepository.getMovies()).thenReturn(movie)
        val movieEntity = viewModel.getMovies().value?.data
        verify(showRepository).getMovies()
        assertNotNull(movieEntity)
        assertEquals(3, movieEntity?.size)
        viewModel.getMovies().observeForever(observer)
        verify(observer).onChanged(dataMovie)
    }
}