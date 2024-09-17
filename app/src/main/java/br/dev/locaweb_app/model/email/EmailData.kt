package br.dev.locaweb_app.model.email

import java.time.LocalDateTime

data class EmailData(
    val id: Int,
    val sendByUser: String,
    val receiveByUser: String,
    val subject: String,
    val sendAt: String,
    val wasRead: Boolean
)
