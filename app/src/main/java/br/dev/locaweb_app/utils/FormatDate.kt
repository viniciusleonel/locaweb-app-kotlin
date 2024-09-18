package br.dev.locaweb_app.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

fun formatDate(dateString: String): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
    val dateTime = LocalDateTime.parse(dateString.substring(0, 19), formatter)
    val outputFormatter = DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy, HH:mm", Locale("pt", "BR"))
    return dateTime.format(outputFormatter)
}

