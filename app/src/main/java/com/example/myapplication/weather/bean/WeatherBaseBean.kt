package com.example.myapplication.weather.bean

data class WeatherBaseBean(
    val count: String?,
    val info: String?,
    val infocode: String?,
    val lives: List<LiveItem>?,
    val forecasts: List<Forecast>?,
    val status: String?
)