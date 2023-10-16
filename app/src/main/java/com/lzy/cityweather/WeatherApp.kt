package com.lzy.cityweather

import android.app.Application

/**
 * @author:lizhenya
 * @Time: 10/16/23
 */
class WeatherApp : Application() {
    companion object {
        lateinit var app: Application
    }

    override fun onCreate() {
        super.onCreate()
        app = this
    }
}