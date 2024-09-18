package br.dev.locaweb_app.model.email

import java.time.LocalDateTime

data class ReceivedEmail(
    val id: Long,
    val subject: String,
    val receivedFrom: String,
    val sentAt: LocalDateTime,
    val wasRead: Boolean
)
