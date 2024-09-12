package br.dev.locaweb_app.service.user

import br.dev.locaweb_app.model.user.UserRegister
import br.dev.locaweb_app.model.user.UserRegisterResponse
import br.dev.locaweb_app.service.RetrofitFactory
import br.dev.locaweb_app.service.handleApiCall

fun UserRegister.register(
    onSuccess: (UserRegisterResponse) -> Unit,
    onFailure: (String) -> Unit
) {
    val call = RetrofitFactory().getUserService().register(this)
    handleApiCall(
        call,
        onSuccess = onSuccess,
        onFailure = onFailure
    )
}