package br.dev.locaweb_app.model.email

import java.time.LocalDateTime

data class SentEmail (
    val id: Long = 0,
    val subject: String = "",
    val sendTo: String = "",
    val sentAt: String = "",
    val wasRead: Boolean = false
)
