package com.lzy.cityweather.io

import com.lzy.cityweather.bean.CityWeatherInfo
import com.lzy.cityweather.io.resp.BaseResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author:lizhenya
 * @Time: 10/16/23
 */
interface WeatherService {

    @GET("v3/weather/weatherInfo?key=fa93c431894b380ab56c5d4e38a703ea")
    suspend fun queryWeather(@Query("city") code: String): BaseResponse<List<CityWeatherInfo>>
}