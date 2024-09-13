package br.dev.locaweb_app.model.email

import java.time.LocalDateTime

data class SentEmail (
    val id: Long,
    val subject: String,
    val sendTo: String,
    val sentAt: LocalDateTime,
    val wasRead: Boolean
)
