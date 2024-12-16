package com.example.newfit.utils

import java.text.SimpleDateFormat
import java.util.Calendar

object TimeUtils {
    val formatter = SimpleDateFormat("mm:ss")
    fun setTimer(time: Long): String {
        val cv = Calendar.getInstance()
        cv.timeInMillis = time
        return formatter.format(cv.time)
    }


}