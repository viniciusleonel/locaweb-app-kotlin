package br.dev.locaweb_app.model.email

data class Email(
    var sentByUser: String,
    var receivedByUser: String,
    var subject: String,
    var body: String,
)

