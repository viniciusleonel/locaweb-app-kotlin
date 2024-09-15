package br.dev.locaweb_app.service.preferences

import br.dev.locaweb_app.model.preferences.UserPreferencesResponse
import br.dev.locaweb_app.service.RetrofitFactory
import br.dev.locaweb_app.service.handleApiCall

fun loadUserPreferences(
    id: Long,
    onSuccess: (UserPreferencesResponse) -> Unit,
    onFailure: (String) -> Unit
) {
    val call = RetrofitFactory().getPreferencesService().loadUserPreferences(id)
    handleApiCall(
        call,
        onSuccess = onSuccess,
        onFailure = onFailure
    )
}