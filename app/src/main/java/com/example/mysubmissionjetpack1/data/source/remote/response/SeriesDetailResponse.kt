package com.example.mysubmissionjetpack1.data.source.remote.response

class SeriesDetailResponse (
        val id: Int,
        val name: String,
        val overview: String,
        val poster_path: String,
        val first_air_date: String,
        val genres: List<SeriesGenres>,
        val vote_average: Double
    )

data class SeriesGenres (
        val id: Int,
        val name: String
    )
