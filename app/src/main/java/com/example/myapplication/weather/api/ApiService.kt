package com.example.myapplication.weather.api

import com.example.myapplication.weather.bean.WeatherBaseBean
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by hml on 2023/4/6.
 * API
 */
interface ApiService {

    /***
     * 获取提天气接口
     */
    @GET("v3/weather/weatherInfo")
    suspend fun getForecastWeather(@Query("city") city:String, @Query("key") key:String="5c27202b097becd3535c245bbeea8365", @Query("extensions") extensions:String = "all"): WeatherBaseBean

}
