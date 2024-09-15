package br.dev.locaweb_app.model.email

import java.time.LocalDateTime

data class SentEmail (
    val id: Long = 0,
    val subject: String = "",
    val sendTo: String = "",
    val sentAt: LocalDateTime = LocalDateTime.of(2023, 9, 15, 12, 30),
    val wasRead: Boolean = false
)
