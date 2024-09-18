package br.dev.locaweb_app.utils

val routeTitles = mapOf(
    "emailView/{emailId}" to "View Email",
    "received-emails" to "Inbox",
    "edit-profile" to "Edit Profile",
    "sent-emails" to "Outbox",
    "send-email" to "Send Email",
    "settings" to "Settings",
    "theme" to "Theme",
)

fun getRouteTitle(currentRoute: String?): String {
    return routeTitles[currentRoute] ?: "Unknown route"
}
