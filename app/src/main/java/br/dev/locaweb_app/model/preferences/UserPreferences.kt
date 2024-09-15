package br.dev.locaweb_app.model.preferences

data class UserPreferences (
    val id: Long? = 0,
    val theme: String?,
    val colorScheme: String?,
    val categories: String?,
    val labels: String?,
    val userId: Long = 0
)
data class UserPreferencesResponse(
    val id: Long? = 0,
    val theme: String? = "",
    val colorScheme: String? = "",
    val categories: String? = "",
    val labels: String? = ""
)
