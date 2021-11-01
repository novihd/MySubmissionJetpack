package com.example.mysubmissionjetpack1.utils

import com.example.mysubmissionjetpack1.data.source.local.entity.MovieEntity
import com.example.mysubmissionjetpack1.data.source.local.entity.TvEntity
import com.example.mysubmissionjetpack1.data.source.remote.response.*

object DataShow {

    fun getMovie() : List<MovieEntity> {
        val movies = ArrayList<MovieEntity>()

        movies.add(
                MovieEntity( 1,
                        "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/wrFpXMNBRj2PBiN4Z5kix51XaIZ.jpg",
                        "A Star Is Born",
                        "2018-05-10",
                        7.5,
                        "Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.",
                        "2018-05-10",
                false
                )
        )
        return movies
    }

    fun getTv() : List<TvEntity> {
        val tvSeries = ArrayList<TvEntity>()
        tvSeries.add(
                TvEntity(2,
                        "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/9akij7PqZ1g6zl42DQQTtL9CTSb.jpg",
                        "Shameless",
                        "Drama, Comedy",
                        8.0,
                        "Chicagoan Frank Gallagher is the proud single dad of six smart, industrious, independent kids, who without him would be... perhaps better off. When Frank's not at the bar spending what little money they have, he's passed out on the floor. But the kids have found ways to grow up in spite of him. They may not be like any family you know, but they make no apologies for being exactly who they are.",
                        "2018-05-10",
                        false
                )
        )

        return tvSeries
    }

    fun getMovieDetail() : List<MovieEntity> {
        val movie = ArrayList<MovieEntity>()
        movie.add(
                MovieEntity(1,
                        "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/wrFpXMNBRj2PBiN4Z5kix51XaIZ.jpg",
                        "A Star Is Born",
                        "Drama, Romance, Music",
                        7.5,
                        "Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.",
                        "2018-05-10"
                )
        )
        return movie
    }

    fun getSeriesDetail() : List<TvEntity> {
        val series = ArrayList<TvEntity>()
        series.add(
                TvEntity(2,
                        "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/9akij7PqZ1g6zl42DQQTtL9CTSb.jpg",
                        "Shameless",
                        "Drama, Comedy",
                        8.0,
                        "Chicagoan Frank Gallagher is the proud single dad of six smart, industrious, independent kids, who without him would be... perhaps better off. When Frank's not at the bar spending what little money they have, he's passed out on the floor. But the kids have found ways to grow up in spite of him. They may not be like any family you know, but they make no apologies for being exactly who they are.",
                        "2011-01-09"
                )
        )
        return series
    }

    fun getRemoteMovie() : List<MoviesResult> {
        val movie = ArrayList<MoviesResult>()
        movie.add(
                MoviesResult(1,
                        "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/wrFpXMNBRj2PBiN4Z5kix51XaIZ.jpg",
                        "A Star Is Born",
                        "Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.",
                7.5,
                        "2018-05-10"
                )
        )
        return movie
    }

    fun getRemoteSeries() : List<SeriesResult> {
        val series = ArrayList<SeriesResult>()
        series.add(
                SeriesResult(2,
                        "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/9akij7PqZ1g6zl42DQQTtL9CTSb.jpg",
                        "Shameless",
                        "Chicagoan Frank Gallagher is the proud single dad of six smart, industrious, independent kids, who without him would be... perhaps better off. When Frank's not at the bar spending what little money they have, he's passed out on the floor. But the kids have found ways to grow up in spite of him. They may not be like any family you know, but they make no apologies for being exactly who they are.",
                        8.0,
                        "2011-01-09"
                )
        )
        return series
    }

    fun getRemoteMovieDetail(movieId: Int) : List<MovieDetailResponse> {
        val movie = ArrayList<MovieDetailResponse>()
        movie.add(
                MovieDetailResponse(movieId,
                        "A Star Is Born",
                        "Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.",
                        "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/wrFpXMNBRj2PBiN4Z5kix51XaIZ.jpg",
                        "2018-05-10",
                        listOf(
                                MovieGenres(1, "Drama, Romance, Music")
                        ),
                        7.5
                )
        )
        return movie
    }

    fun getRemoteSeriesDetail() : List<SeriesDetailResponse> {
        val series = ArrayList<SeriesDetailResponse>()
        series.add(
                SeriesDetailResponse(2,
                        "Shameless",
                        "Chicagoan Frank Gallagher is the proud single dad of six smart, industrious, independent kids, who without him would be... perhaps better off. When Frank's not at the bar spending what little money they have, he's passed out on the floor. But the kids have found ways to grow up in spite of him. They may not be like any family you know, but they make no apologies for being exactly who they are.",
                        "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/9akij7PqZ1g6zl42DQQTtL9CTSb.jpg",
                        "2011-01-09",
                        listOf(
                                SeriesGenres(1, "Drama, Comedy")
                        ),
                        8.0
                )
        )
        return series
    }
}