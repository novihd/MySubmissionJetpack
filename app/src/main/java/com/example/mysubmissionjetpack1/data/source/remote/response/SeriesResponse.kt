package com.example.mysubmissionjetpack1.data.source.remote.response

data class SeriesResponse (
    val page: Int,
    val results: List<SeriesResult>
)

data class SeriesResult (
    val id: Int,
    val poster_path: String,
    val name: String,
    val overview: String,
    val vote_average: Double,
    val first_air_date: String
    )

