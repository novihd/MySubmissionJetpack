package com.example.mysubmissionjetpack1.helper

import com.example.mysubmissionjetpack1.data.source.remote.response.MovieDetailResponse
import com.example.mysubmissionjetpack1.data.source.remote.response.SeriesDetailResponse

object JsonHelper {
    fun genreToString(movieDetailResponse: MovieDetailResponse): StringBuilder {
        val genres = StringBuilder()
        var separator = false
        val genreMap = movieDetailResponse.genres.map {
            it.name
        }
        for (genre in genreMap) {
            if (separator) genres.append(", ")
            separator = true
            genres.append(genre)

        }
        return genres
    }

    fun genreTvToString(seriesDetailResponse: SeriesDetailResponse): StringBuilder {
        val genres = StringBuilder()
        var separator = false
        val genreMap = seriesDetailResponse.genres.map {
            it.name
        }
        for (genre in genreMap) {
            if (separator) genres.append(", ")
            separator = true
            genres.append(genre)

        }
        return genres
    }
}

