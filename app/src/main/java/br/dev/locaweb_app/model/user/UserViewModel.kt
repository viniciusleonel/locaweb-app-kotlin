package br.dev.locaweb_app.model.user

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {
    var userLoginResponse = mutableStateOf<UserLoginResponse?>(null)
        private set

    fun setUserLoginResponse(response: UserLoginResponse) {
        userLoginResponse.value = response
    }

    fun updateUserProfile(name: String, username: String, email: String) {

    }
}
