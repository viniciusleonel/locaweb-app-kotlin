package br.dev.locaweb_app.ui.components

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

enum class SnackBarStatus {
    SUCCESS,
    ERROR,
    REGULAR,
    NONE
}

class SnackBarViewModel : ViewModel() {
    var snackBarState = mutableStateOf(SnackBarStatus.NONE)
        private set

    fun showSuccessSnackbar() {
        snackBarState.value = SnackBarStatus.SUCCESS
    }

    fun showErrorSnackbar() {
        snackBarState.value = SnackBarStatus.ERROR
    }

    fun showRegularSnackbar() {
        snackBarState.value = SnackBarStatus.REGULAR
    }

    fun hideSnackbar() {
        snackBarState.value = SnackBarStatus.NONE
    }
}
