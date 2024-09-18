package br.dev.locaweb_app.model.user

data class UserRegister(
    val name: String,
    val email: String,
    val username: String,
    val password: String
)

data class UserRegisterResponse(
    val password: String? = null,
    val name: String? = null,
    val email: String? = null,
    val username: String? = null
)


