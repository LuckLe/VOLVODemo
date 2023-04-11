package com.example.myapplication.utils

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by hml on 2023/4/11.
 */
object DataUtils {


    /*
* 将时间戳转换为时间
*
* s就是时间戳
*/
    fun stampToDate(s: String): String? {
        val res: String
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        //如果它本来就是long类型的,则不用写这一步
        val lt: Long = s.toLong()
        val date = Date(lt)
        res = simpleDateFormat.format(date)
        return res
    }

}