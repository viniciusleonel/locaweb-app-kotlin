package br.dev.locaweb_app.model.user

data class UserUpdate(
    val name: String? = null,
    val email: String? = null,
    val username: String? = null,
    val password: String? = null
)