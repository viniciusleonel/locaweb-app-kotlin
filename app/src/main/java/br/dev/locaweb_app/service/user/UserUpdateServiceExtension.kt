package br.dev.locaweb_app.service.user

import br.dev.locaweb_app.model.user.UserLoginResponse
import br.dev.locaweb_app.model.user.UserUpdate
import br.dev.locaweb_app.service.RetrofitFactory
import br.dev.locaweb_app.service.handleApiCall

fun UserUpdate.update(
    id: Long,
    onSuccess: (UserLoginResponse) -> Unit,
    onFailure: (String) -> Unit
) {
    val call = RetrofitFactory().getUserService().updateUserById(id, this)
    handleApiCall(
        call,
        onSuccess = onSuccess,
        onFailure = onFailure
    )
}