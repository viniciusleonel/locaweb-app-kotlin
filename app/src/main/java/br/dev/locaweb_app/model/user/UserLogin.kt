package br.dev.locaweb_app.model.user

import br.dev.locaweb_app.model.preferences.UserPreferences

data class UserLogin(
    val username: String,
    val password: String
)

data class UserLogout(
    val username: String,
)

data class UserLoginResponse(
    val id: Long = 0,
    val name: String = "",
    val username: String = "",
    val email: String = "",
    val isLoggedIn: Boolean = false,
    val userPreferences: UserPreferences? = null
)
