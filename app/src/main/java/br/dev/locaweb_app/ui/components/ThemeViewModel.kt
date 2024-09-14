package br.dev.locaweb_app.ui.components

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class ThemeViewModel : ViewModel() {
    val isDarkTheme = mutableStateOf(false)

    fun toggleTheme() {
        isDarkTheme.value = !isDarkTheme.value
    }

    fun setLightTheme() {
        isDarkTheme.value = false
    }

    fun setDarkTheme() {
        isDarkTheme.value = true
    }
}

