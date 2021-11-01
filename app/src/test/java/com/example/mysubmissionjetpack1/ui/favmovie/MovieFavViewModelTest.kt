package com.example.mysubmissionjetpack1.ui.favmovie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.mysubmissionjetpack1.data.ShowRepository
import com.example.mysubmissionjetpack1.data.source.local.entity.MovieEntity
import com.example.mysubmissionjetpack1.helper.SortUtil.OLDEST
import com.example.mysubmissionjetpack1.ui.favorite.MovieFavViewModel
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieFavViewModelTest {

    private lateinit var viewModel: MovieFavViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var showRepository: ShowRepository

    @Mock
    private lateinit var pagedList: PagedList<MovieEntity>

    @Mock
    private lateinit var observer: Observer<PagedList<MovieEntity>>

    @Before
    fun setUp() {
        viewModel = MovieFavViewModel(showRepository)
    }

    @Test
    fun getFavMovie() {
        val dataFavMovie = pagedList
        `when`(dataFavMovie.size).thenReturn(2)
        val favMovie = MutableLiveData<PagedList<MovieEntity>>()
        favMovie.value = dataFavMovie
        `when`(showRepository.getFavMovies(OLDEST)).thenReturn(favMovie)
        val movieEntity = viewModel.getMovieFav(OLDEST).value
        verify(showRepository).getFavMovies(OLDEST)
        assertNotNull(movieEntity)
        assertEquals(2, movieEntity?.size)
        viewModel.getMovieFav(OLDEST).observeForever(observer)
        verify(observer).onChanged(dataFavMovie)
    }
}