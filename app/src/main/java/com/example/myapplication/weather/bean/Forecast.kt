package com.example.myapplication.weather.bean

data class Forecast(
    val adcode: String?,
    val casts: MutableList<CastItem>?,
    val city: String?,
    val province: String?,
    val reporttime: String?
)