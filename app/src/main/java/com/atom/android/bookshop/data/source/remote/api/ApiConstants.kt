package com.atom.android.bookshop.data.source.remote.api

object ApiConstants {

    object Endpoint {
        const val BOOK = "book"
        const val AUTHOR = "author"
        const val GENRE = "genre"
        const val PUBLISHER = "publisher"
        const val LOGIN = "login"
    }

    object Method {
        const val POST = "POST"
        const val GET = "GET"
        const val PUT = "PUT"
        const val DELETE = "DELETE"
    }

    object Response {
        const val DATA = "data"
        const val STATUS = "status"
        const val MESSAGE = "message"
    }

    const val CONNECTION_TIME = 5000

    object ERROR {
        const val ERROR_MESSAGE_TIMEOUT = "Lỗi kết nối server"
        const val ERROR_MESSAGE_BAD_URL = "Kết nối không hợp lệ"
        const val ERROR_MESSAGE_IO = "Dữ liệu không hợp lệ"
        const val ERROR_MESSAGE_JSON_CONVERT_FAILED = "Dữ liệu không hợp lệ"
    }

    object ATTRIBUTE{
        const val CONTENT_TYPE = "Content-Type"
        const val APPLICATION_JSON = "application/json"
        const val ACCEPT = "Accept"
        const val CHARSET = "charset"
        const val AUTHORIZATION = "Authorization"
        const val BEARER = "Bearer"
        const val MODE_CHARSET = "utf-8"
    }
    object DIALOGCONFIG{
        const val MARGIN_Y = -170
    }
}