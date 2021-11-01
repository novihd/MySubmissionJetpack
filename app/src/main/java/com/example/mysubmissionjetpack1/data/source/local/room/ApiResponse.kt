package com.example.mysubmissionjetpack1.data.source.local.room
import com.example.mysubmissionjetpack1.data.source.local.room.StatusResponse.EMPTY
import com.example.mysubmissionjetpack1.data.source.local.room.StatusResponse.ERROR
import com.example.mysubmissionjetpack1.data.source.local.room.StatusResponse.SUCCESS


class ApiResponse <T>(val status: StatusResponse, val body: T, val message: String?) {
    companion object {
        fun <T> success(body: T): ApiResponse<T> = ApiResponse(SUCCESS, body, null)

        fun <T> empty(msg: String, body: T): ApiResponse<T> = ApiResponse(EMPTY, body, msg)

        fun <T> error(msg: String, body: T): ApiResponse<T> = ApiResponse(ERROR, body, msg)
    }
}