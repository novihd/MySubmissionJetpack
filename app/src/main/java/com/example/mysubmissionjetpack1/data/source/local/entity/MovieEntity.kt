package com.example.mysubmissionjetpack1.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_entity")
class MovieEntity (
        @PrimaryKey
        @NonNull
        @ColumnInfo
        val showId: Int,

        @NonNull
        @ColumnInfo
        val poster: String?,

        @NonNull
        @ColumnInfo
        val title: String,

        @NonNull
        @ColumnInfo
        val genre: String?,

        @NonNull
        @ColumnInfo
        val rating: Double,

        @NonNull
        @ColumnInfo
        val description: String?,

        @NonNull
        @ColumnInfo
        val date: String,

        @ColumnInfo
        var isFavorite: Boolean = false
        )
