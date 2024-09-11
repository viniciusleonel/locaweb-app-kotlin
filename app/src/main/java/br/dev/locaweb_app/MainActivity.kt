package br.dev.locaweb_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import br.dev.locaweb_app.ui.screens.MainScreen
import br.dev.locaweb_app.ui.theme.LocawebappTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LocawebappTheme {
                MainScreen()
            }
        }
    }
}
