package com.example.mysubmissionjetpack1.helper

import androidx.sqlite.db.SimpleSQLiteQuery

object SortUtil {
    const val NEWEST = "Newest"
    const val OLDEST = "Oldest"

    fun getMovieSortQuery(filter: String): SimpleSQLiteQuery{
        val query = StringBuilder().append("SELECT * FROM movie_entity WHERE isFavorite = 1 ")
        when (filter) {
            NEWEST -> query.append("ORDER BY date DESC")
            OLDEST -> query.append("ORDER BY date ASC")
        }
        return SimpleSQLiteQuery(query.toString())
    }

    fun getSeriesSortQuery(filter: String): SimpleSQLiteQuery{
        val query = StringBuilder().append("SELECT * FROM tv_entity WHERE isFavorite = 1 ")
        when (filter) {
            NEWEST -> query.append("ORDER BY date DESC")
            OLDEST -> query.append("ORDER BY date ASC")
        }
        return SimpleSQLiteQuery(query.toString())
    }
}