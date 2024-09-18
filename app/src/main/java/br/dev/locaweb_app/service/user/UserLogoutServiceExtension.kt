package br.dev.locaweb_app.service.user

import androidx.navigation.NavController
import br.dev.locaweb_app.model.MessageResponse
import br.dev.locaweb_app.model.user.UserLogout
import br.dev.locaweb_app.service.RetrofitFactory
import br.dev.locaweb_app.service.handleApiCall

fun logout(
    userLogout: UserLogout,
    onSuccess: (MessageResponse) -> Unit,
    onFailure: (String) -> Unit,
    navController: NavController
) {
    val call = RetrofitFactory().getUserService().logout(userLogout)
    handleApiCall(
        call,
        onSuccess = onSuccess,
        onFailure = onFailure,
        navController = navController
    )
}