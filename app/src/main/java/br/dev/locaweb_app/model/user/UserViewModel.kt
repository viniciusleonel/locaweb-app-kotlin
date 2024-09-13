package br.dev.locaweb_app.model.user

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {
    var userLoginResponse = mutableStateOf<UserLoginResponse?>(null)
        private set

    fun setUserLoginResponse(response: UserLoginResponse) {
        userLoginResponse.value = response
    }

    var userDetails = mutableStateOf<UserDetails?>(null)
        private set

    fun setUserDetails(response: UserDetails) {
        userDetails.value = response
    }
}
