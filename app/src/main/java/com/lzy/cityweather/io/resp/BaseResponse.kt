package com.lzy.cityweather.io.resp

import androidx.annotation.Keep

/**
 * @author:lizhenya
 * @Time: 2021/8/22
 * @email: 1556342503@qq.com
 */
@Keep
class BaseResponse<T>(
    var status: String,
    var info: String? = "",
    var lives: T?
)