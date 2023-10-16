package com.lzy.cityweather.io

import android.content.Context
import com.lzy.cityweather.config.Constant
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object HttpClient {
    private const val TIME_OUT: Long = 3000

    fun getRetrofit(context: Context): Retrofit {
        return getRetrofit(context.applicationContext, Constant.BASE_URL)
    }

    fun getRetrofit(context: Context, baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(getOKHttpClient(context))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getRxRetrofit(context: Context, baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(getOKHttpClient(context))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getOKHttpClient(context: Context): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .connectTimeout(TIME_OUT, TimeUnit.MILLISECONDS)
            .writeTimeout(TIME_OUT, TimeUnit.MILLISECONDS)
            .readTimeout(TIME_OUT, TimeUnit.MILLISECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }
}