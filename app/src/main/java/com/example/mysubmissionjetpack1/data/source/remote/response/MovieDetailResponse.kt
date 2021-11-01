package com.example.mysubmissionjetpack1.data.source.remote.response

data class MovieDetailResponse (
        val id: Int,
        val title: String,
        val overview: String,
        val poster_path: String,
        val release_date: String,
        val genres: List<MovieGenres>,
        val vote_average: Double
)

data class MovieGenres (
        val id: Int,
        val name: String
        )