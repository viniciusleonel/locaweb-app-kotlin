package br.dev.locaweb_app.model.email

data class EmailData(
    val id: Long,
    val sendByUser: String,
    val receiveByUser: String,
    val subject: String,
    val sendAt: String,
    val wasRead: Boolean
)
