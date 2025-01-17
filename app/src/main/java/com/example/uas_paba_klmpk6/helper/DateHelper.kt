package com.example.uas_paba_klmpk6.helper

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateHelper {
    fun getCurrentDate() : String{
        val dateFormat = SimpleDateFormat(
            "yyyy/MM/dd HH:mm:ss",
            Locale.getDefault()
        )
        val date = Date()
        return dateFormat.format(date)
    }
    fun getCurrentFormatDate() : String {
        val dateFormat = SimpleDateFormat(
            "dd - MM - yyyy",
            Locale.getDefault()
        )
        val date = Date()
        return dateFormat.format(date)
    }
}