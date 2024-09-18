package br.dev.locaweb_app.service.email

import androidx.navigation.NavController
import br.dev.locaweb_app.model.email.EmailDetails
import br.dev.locaweb_app.service.RetrofitFactory
import br.dev.locaweb_app.service.handleApiCall

fun getEmailByIdAndUserId(
    id: Long,
    userId: Long,
    onSuccess: (EmailDetails) -> Unit,
    onFailure: (String) -> Unit,
    navController: NavController
) {
    val call = RetrofitFactory().getEmailService().getEmailByIdAndUserId(id, userId)
    handleApiCall(
        call,
        onSuccess = onSuccess,
        onFailure = onFailure,
        navController = navController
    )
}