package br.dev.locaweb_app.model.user

// UserViewModel.kt
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {
    var userLoginResponse = mutableStateOf<UserLoginResponse?>(null)
        private set

    fun setUserLoginResponse(response: UserLoginResponse) {
        userLoginResponse.value = response
    }
}
