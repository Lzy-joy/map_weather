package com.lzy.cityweather.io.resp

import kotlinx.coroutines.TimeoutCancellationException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

object ExceptionHandler {
    fun handle(e: Throwable): AppNetIOException {
        return when (e) {
            is TimeoutCancellationException, is SocketTimeoutException, is UnknownHostException, is ConnectException -> {//网络异常
                AppNetIOException(1111, "网络异常")
            }

            else -> {
                AppNetIOException(1111, "系统异常")
            }
        }
    }
}