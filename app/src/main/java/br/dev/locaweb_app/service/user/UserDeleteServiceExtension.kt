package br.dev.locaweb_app.service.user

import br.dev.locaweb_app.model.MessageResponse
import br.dev.locaweb_app.service.RetrofitFactory
import br.dev.locaweb_app.service.handleApiCall

fun deleteUserById(
    id: Long,
    onSuccess: (MessageResponse) -> Unit,
    onFailure: (String) -> Unit
) {
    val call = RetrofitFactory().getUserService().deleteUserById(id)
    handleApiCall(
        call,
        onSuccess = onSuccess,
        onFailure = onFailure
    )
}