package br.dev.locaweb_app.service.email

import androidx.navigation.NavController
import br.dev.locaweb_app.model.MessageResponse
import br.dev.locaweb_app.service.RetrofitFactory
import br.dev.locaweb_app.service.handleApiCall

fun deleteEmailById(
    id: Long,
    onSuccess: (MessageResponse) -> Unit,
    onFailure: (String) -> Unit,
    navController: NavController
) {
    val call = RetrofitFactory().getEmailService().deleteEmailById(id)
    handleApiCall(
        call,
        onSuccess = onSuccess,
        onFailure = onFailure,
        navController = navController
    )
}
