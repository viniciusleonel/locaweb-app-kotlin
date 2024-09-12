package br.dev.locaweb_app.service.user

import br.dev.locaweb_app.model.user.UserLogin
import br.dev.locaweb_app.model.user.UserLoginResponse
import br.dev.locaweb_app.service.RetrofitFactory
import br.dev.locaweb_app.service.handleApiCall

fun UserLogin.login(
    onSuccess: (UserLoginResponse) -> Unit,
    onFailure: (String) -> Unit
) {
    val call = RetrofitFactory().getUserService().login(this)
    handleApiCall(
        call,
        onSuccess = onSuccess,
        onFailure = onFailure
    )
}