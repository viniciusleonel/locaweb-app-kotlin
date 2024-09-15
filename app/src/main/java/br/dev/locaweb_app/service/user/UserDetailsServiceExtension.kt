package br.dev.locaweb_app.service.user

import androidx.navigation.NavController
import br.dev.locaweb_app.model.user.UserDetails
import br.dev.locaweb_app.service.RetrofitFactory
import br.dev.locaweb_app.service.handleApiCall

fun getUserById(
    id: Long,
    onSuccess: (UserDetails) -> Unit,
    onFailure: (String) -> Unit,
    navController: NavController
) {
    val call = RetrofitFactory().getUserService().getUserById(id)
    handleApiCall(
        call,
        onSuccess = onSuccess,
        onFailure = onFailure,
        navController = navController
    )
}
