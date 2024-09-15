package br.dev.locaweb_app.service.email

import androidx.navigation.NavController
import br.dev.locaweb_app.model.email.Email
import br.dev.locaweb_app.model.email.SentEmail
import br.dev.locaweb_app.service.RetrofitFactory
import br.dev.locaweb_app.service.handleApiCall

fun Email.sendEmail(
    onSuccess: (SentEmail) -> Unit,
    onFailure: (String) -> Unit,
    navController: NavController
) {
    val call = RetrofitFactory().getEmailService().sendEmail(this)
    handleApiCall(
        call,
        onSuccess = onSuccess,
        onFailure = onFailure,
        navController = navController
    )
}