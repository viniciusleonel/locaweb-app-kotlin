package br.dev.locaweb_app.ui.components

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import br.dev.locaweb_app.ui.theme.OceanBlue

class ThemeViewModel : ViewModel() {

    private val _isDarkTheme = mutableStateOf(false)
    val isDarkTheme: State<Boolean> = _isDarkTheme

    private val _selectedColor = mutableStateOf(OceanBlue)
    val selectedColor: State<Color> = _selectedColor

    private val _navBarColor = mutableStateOf(OceanBlue)
    val navBarColor: State<Color> = _navBarColor

    fun toggleTheme() {
        _isDarkTheme.value = !_isDarkTheme.value
    }

    fun updateColor(color: Color) {
        _selectedColor.value = color
        _navBarColor.value = color
    }
}


