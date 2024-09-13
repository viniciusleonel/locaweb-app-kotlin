package br.dev.locaweb_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import br.dev.locaweb_app.ui.components.ThemeViewModel
import br.dev.locaweb_app.ui.screens.MainScreen
import br.dev.locaweb_app.ui.theme.LocawebappTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val themeViewModel: ThemeViewModel = viewModel()
            val isDarkTheme by themeViewModel.isDarkTheme

            LocawebappTheme(darkTheme = isDarkTheme) {
                MainScreen(
                    themeViewModel = themeViewModel // Passe o viewModel para a MainScreen
                )
            }
        }
    }
}
