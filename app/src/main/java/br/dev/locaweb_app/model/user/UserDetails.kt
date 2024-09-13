package br.dev.locaweb_app.model.user

import br.dev.locaweb_app.model.email.ReceivedEmail
import br.dev.locaweb_app.model.email.SentEmail
import br.dev.locaweb_app.model.preferences.UserPreferences
import java.time.LocalDateTime

data class UserDetails(
    val id: Long,
    val name: String,
    val username: String,
    val email: String,
    val isLoggedIn: Boolean,
    val lastLogin: LocalDateTime,
    val status: Boolean,
    val userPreferences: MutableList<UserPreferences>,
    val sentEmails: MutableList<SentEmail>,
    val receivedEmails: MutableList<ReceivedEmail>
)
