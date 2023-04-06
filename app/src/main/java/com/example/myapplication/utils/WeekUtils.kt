package com.example.myapplication.utils

/**
 * Created by hml on 2023/4/6.
 */
object WeekUtils {
    fun getWeek(week:String):String{
        return when(week.toInt()){
            1 -> "一"
            2 -> "二"
            3 -> "三"
            4 -> "四"
            5 -> "五"
            6 -> "六"
            7 -> "日"
            else -> "--"
        }
    }
}