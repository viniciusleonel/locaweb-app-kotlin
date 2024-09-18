package br.dev.locaweb_app.model.email

data class EmailDetails(
    val id: Int,
    val sendByUser: String,
    val receiveByUser: String,
    val subject: String,
    val body: String,
    val sendAt: String,
    val wasRead: Boolean
)