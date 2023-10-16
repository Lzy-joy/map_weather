package com.lzy.cityweather.io.resp

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.EmptyCoroutineContext

fun <T> CoroutineScope.singleRequest(block: suspend () -> BaseResponse<T>,success: (t: T?) -> Unit, failure: (AppNetIOException) -> Unit = {} ): Job {
    return this.launch(context = EmptyCoroutineContext, start = CoroutineStart.DEFAULT) {
        kotlin.runCatching {
            block()
        }.onFailure {
            failure(ExceptionHandler.handle(it))
        }.onSuccess { response ->
            if (response.status == "1") {
                success(response.lives)
            } else {
                failure(AppNetIOException(0, response.info ?: "系统异常"))
            }
        }
    }
}
