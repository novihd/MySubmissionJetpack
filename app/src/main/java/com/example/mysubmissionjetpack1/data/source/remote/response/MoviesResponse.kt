package com.example.mysubmissionjetpack1.data.source.remote.response

data class MoviesResponse (
    val page: Int,
    val results: List<MoviesResult>
        )

data class MoviesResult (
    val id: Int,
    val poster_path: String,
    val title: String,
    val overview: String,
    val vote_average: Double,
    val release_date: String
    )

