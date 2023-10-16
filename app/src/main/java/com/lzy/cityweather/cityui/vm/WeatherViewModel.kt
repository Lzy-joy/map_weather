package com.lzy.cityweather.cityui.vm

import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lzy.cityweather.WeatherApp
import com.lzy.cityweather.bean.CityWeatherInfo
import com.lzy.cityweather.io.resp.singleRequest
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * @author:lizhenya
 * @Time: 10/16/23
 */
class WeatherViewModel : ViewModel() {
    val map = hashMapOf(
        "110000" to MutableStateFlow(CityWeatherInfo()),
        "310000" to MutableStateFlow(CityWeatherInfo()),
        "440100" to MutableStateFlow(CityWeatherInfo()),
        "440300" to MutableStateFlow(CityWeatherInfo()),
        "320500" to MutableStateFlow(CityWeatherInfo()),
        "210100" to MutableStateFlow(CityWeatherInfo())
    )


    //    private var lastWeatherInfo = CityWeatherInfo()
//    val weatherInfoState = MutableStateFlow(lastWeatherInfo)


    fun getWeather(code: String) {
        val last = map[code] as? CityWeatherInfo ?: CityWeatherInfo()
        viewModelScope.singleRequest({
            WeatherApi.queryWeather(code)
        }, { weatherList ->
            val cityWeatherInfos = weatherList ?: return@singleRequest
            if (cityWeatherInfos.isEmpty()) {
                return@singleRequest
            }
            val cityWeatherInfo = cityWeatherInfos[0]
            map[code]?.compareAndSet(last, cityWeatherInfo)
        }, { errorMsg ->
            Toast.makeText(WeatherApp.app, "$errorMsg", Toast.LENGTH_SHORT).show()
        })
    }
}