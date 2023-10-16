package com.lzy.cityweather.bean

import java.io.Serializable

/**
 * @author:lizhenya
 * @Time: 10/16/23
 */
data class CityWeatherInfo(
    var province: String? = "",
    var city: String? = "",
    var adcode: String? = "",
    var weather: String? = "",
    var temperature: String? = ""
) : Serializable