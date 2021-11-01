package com.example.mysubmissionjetpack1.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.mysubmissionjetpack1.vo.Resource
import com.example.mysubmissionjetpack1.data.source.local.LocalDataSource
import com.example.mysubmissionjetpack1.data.source.local.entity.MovieEntity
import com.example.mysubmissionjetpack1.data.source.local.entity.TvEntity
import com.example.mysubmissionjetpack1.helper.JsonHelper.genreToString
import com.example.mysubmissionjetpack1.helper.JsonHelper.genreTvToString
import com.example.mysubmissionjetpack1.data.source.remote.RemoteDataSource
import com.example.mysubmissionjetpack1.helper.SortUtil.OLDEST
import com.example.mysubmissionjetpack1.utils.DataShow.getRemoteMovie
import com.example.mysubmissionjetpack1.utils.DataShow.getRemoteMovieDetail
import com.example.mysubmissionjetpack1.utils.DataShow.getRemoteSeries
import com.example.mysubmissionjetpack1.utils.DataShow.getRemoteSeriesDetail
import com.example.mysubmissionjetpack1.utils.LiveDataTest.getValue
import com.example.mysubmissionjetpack1.util.PagedListUtil.mockPagedList
import com.example.mysubmissionjetpack1.utils.AppExecutors
import com.example.mysubmissionjetpack1.utils.DataShow.getMovie
import com.example.mysubmissionjetpack1.utils.DataShow.getMovieDetail
import com.example.mysubmissionjetpack1.utils.DataShow.getSeriesDetail
import com.example.mysubmissionjetpack1.utils.DataShow.getTv

import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

class ShowRepositoryTest {

    @get: Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val movieResponse = getRemoteMovie()
    private val movieId = movieResponse[0].id
    private val detailMovieResponse = getRemoteMovieDetail(movieId)[0]
    private val seriesResponse = getRemoteSeries()
    private val seriesId = seriesResponse[0].id
    private val detailSeriesResponse = getRemoteSeriesDetail()[0]
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)
    private val remote = mock(RemoteDataSource::class.java)
    private val showRepository = FakeShowRepository(remote, local, appExecutors)


    @Test
    @Suppress("UNCHECKED_CAST")
    fun testGetMovies() {
        val dataSource = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getMovies()).thenReturn(dataSource)
        showRepository.getMovies()
        val movieEntity =  Resource.success(mockPagedList(getMovie()))
        verify(local).getMovies()
        assertNotNull(movieEntity.data)
        assertEquals(movieResponse.size, movieEntity.data?.size)
    }

    @Test
    @Suppress("UNCHECKED_CAST")
    fun testGetSeries() {
        val dataSource= mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvEntity>
        `when`(local.getSeries()).thenReturn(dataSource)
        showRepository.getSeries()
        val seriesEntity = Resource.success(mockPagedList(getTv()))
        verify(local).getSeries()
        assertNotNull(seriesEntity)
        assertEquals(seriesResponse.size, seriesEntity.data?.size)
    }

    @Test
    fun testGetDetailMovie() {
        val movieEntity = MutableLiveData<MovieEntity>()
        movieEntity.value = getMovieDetail()[0]
        `when`(local.getDetailMovie(movieId)).thenReturn(movieEntity)
        val detailMovieEntities = getValue(showRepository.getDetailMovie(movieId))
        verify(local).getDetailMovie(movieId)
        assertNotNull(detailMovieEntities)
        assertEquals(detailMovieResponse.id, detailMovieEntities.data?.showId)
        assertEquals(detailMovieResponse.title, detailMovieEntities.data?.title)
        assertEquals(detailMovieResponse.poster_path, detailMovieEntities.data?.poster)
        assertEquals(detailMovieResponse.overview, detailMovieEntities.data?.description)
        assertEquals(detailMovieResponse.release_date, detailMovieEntities.data?.date)
        assertEquals(genreToString(detailMovieResponse).toString(), detailMovieEntities.data?.genre)
        assertEquals(detailMovieResponse.vote_average, detailMovieEntities.data?.rating)
    }

    @Test
    fun testGetDetailTvSeries() {
        val seriesEntity = MutableLiveData<TvEntity>()
        seriesEntity.value = getSeriesDetail()[0]
        `when`(local.getDetailSeries(seriesId)).thenReturn(seriesEntity)
        val detailSeriesEntities = getValue(showRepository.getDetailTvSeries(seriesId))
        verify(local).getDetailSeries(seriesId)
        assertNotNull(detailSeriesEntities)
        assertEquals(detailSeriesResponse.id, detailSeriesEntities.data?.showId)
        assertEquals(detailSeriesResponse.name, detailSeriesEntities.data?.title)
        assertEquals(detailSeriesResponse.poster_path, detailSeriesEntities.data?.poster)
        assertEquals(detailSeriesResponse.overview, detailSeriesEntities.data?.description)
        assertEquals(detailSeriesResponse.first_air_date, detailSeriesEntities.data?.date)
        assertEquals(genreTvToString(detailSeriesResponse).toString(), detailSeriesEntities.data?.genre)
        assertEquals(detailSeriesResponse.vote_average, detailSeriesEntities.data?.rating)
    }

    @Test
    fun testSetFavMovie() {
        val dataMovie = getMovieDetail()[0]
        doNothing().`when`(local).setFavMovie(getMovieDetail()[0], true)
        showRepository.setFavMovie(dataMovie, true)

        verify(local, times(1)).setFavMovie(dataMovie, true)
    }

    @Test
    fun testSetFavSeries() {
        val dataSeries = getSeriesDetail()[0]
        doNothing().`when`(local).setFavSeries(getSeriesDetail()[0], true)
        showRepository.setFavSeries(dataSeries, true)

        verify(local, times(1)).setFavSeries(dataSeries, true)
    }

    @Test
    @Suppress("UNCHECKED_CAST")
    fun testGetFavMovie() {
        val dataSource = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getFavMovies(OLDEST)).thenReturn(dataSource)
        showRepository.getFavMovies(OLDEST)

        val movieEntity = Resource.success((mockPagedList(getMovie())))
        verify(local).getFavMovies(OLDEST)
        assertNotNull(movieEntity.data)
        assertEquals(movieResponse.size, movieEntity.data?.size)
    }

    @Test
    @Suppress("UNCHECKED_CAST")
    fun testGetFavSeries() {
        val dataSource = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvEntity>
        `when`(local.getFavSeries(OLDEST)).thenReturn(dataSource)
        showRepository.getFavSeries(OLDEST)

        val seriesEntity = Resource.success(mockPagedList(getTv()))
        verify(local).getFavSeries(OLDEST)
        assertNotNull(seriesEntity.data)
        assertEquals(seriesResponse.size, seriesEntity.data?.size)
    }


}