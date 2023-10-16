package com.lzy.cityweather.cityui.vm

import com.lzy.cityweather.WeatherApp
import com.lzy.cityweather.bean.CityWeatherInfo
import com.lzy.cityweather.io.HttpClient
import com.lzy.cityweather.io.WeatherService
import com.lzy.cityweather.io.resp.BaseResponse

/**
 * @author:lizhenya
 * @Time: 10/16/23
 */
object WeatherApi {
    private var weatherService: WeatherService =
        HttpClient.getRetrofit(WeatherApp.app).create(WeatherService::class.java)

    suspend fun queryWeather(code: String): BaseResponse<List<CityWeatherInfo>> {
        return weatherService.queryWeather(code)
    }
}