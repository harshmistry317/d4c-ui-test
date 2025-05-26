package com.hkm.d4cshop.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

object Utils {
    fun formatDateRange(startMillis: Long, endMillis: Long): String {
        val utcTimeZone = TimeZone.getTimeZone("UTC")

        val dayFormat = SimpleDateFormat("d", Locale.getDefault()).apply {
            timeZone = utcTimeZone
        }
        val monthFormat = SimpleDateFormat("MMM", Locale.getDefault()).apply {
            timeZone = utcTimeZone
        }
        val fullFormat = SimpleDateFormat("d MMM", Locale.getDefault()).apply {
            timeZone = utcTimeZone
        }

        val startDate = Date(startMillis)
        val endDate = Date(endMillis)

        val sameMonth = monthFormat.format(startDate) == monthFormat.format(endDate)

        return if (sameMonth) {
            "${dayFormat.format(startDate)} - ${dayFormat.format(endDate)} ${monthFormat.format(endDate)}"
        } else {
            "${fullFormat.format(startDate)} - ${fullFormat.format(endDate)}"
        }
    }
}