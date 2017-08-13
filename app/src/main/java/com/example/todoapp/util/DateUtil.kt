package com.example.todoapp.util

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Yasuhiro Suzuki on 2017/06/18.
 */
object DateUtil {

    val PATTERN = "yyyy-MM-dd HH:mm:ss.sss"

    val currentDate: String
        get() = SimpleDateFormat(PATTERN, Locale.JAPAN)
                .format(Calendar.getInstance().time)
                .toString()

    val timestump: Long
        get() = System.currentTimeMillis()

}