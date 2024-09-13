package br.dev.locaweb_app.ui.components

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.State

class ThemeViewModel : ViewModel() {
    private val _isDarkTheme = mutableStateOf(false)
    val isDarkTheme: State<Boolean> = _isDarkTheme

    fun toggleTheme() {
        _isDarkTheme.value = !_isDarkTheme.value
    }
}
