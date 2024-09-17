package br.dev.locaweb_app.service.email

import androidx.navigation.NavController
import br.dev.locaweb_app.model.email.EmailsList
import br.dev.locaweb_app.service.RetrofitFactory
import br.dev.locaweb_app.service.handleApiCall

fun listReceivedEmails(
    id: Long,
    onSuccess: (EmailsList) -> Unit,
    onFailure: (String) -> Unit,
    navController: NavController
) {
    val call = RetrofitFactory().getEmailService().listReceivedEmails(id)
    handleApiCall(
        call,
        onSuccess = onSuccess,
        onFailure = onFailure,
        navController = navController
    )
}