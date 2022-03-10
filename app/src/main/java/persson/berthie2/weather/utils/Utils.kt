package persson.berthie2.weather.utils

import java.text.SimpleDateFormat
import java.util.*

fun formatDate(timestamp: Int): String {
    val sdf = SimpleDateFormat("EEE, MMM, d")
    val date = java.util.Date(timestamp.toLong() * 1000)

    return sdf.format(date)
}

fun formatDateTime(timestamp: Int): String {

    val sdf = SimpleDateFormat("HH:mm",)
    val date = java.util.Date(timestamp.toLong() * 1000)

    return sdf.format(date)
}
fun formatDecimals(item: Double): String {
    return " %.0f".format(item)
}


