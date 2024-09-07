package br.dev.locaweb_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.dev.locaweb_app.navigation.NavGraph
import br.dev.locaweb_app.ui.components.MenuBar
import br.dev.locaweb_app.ui.screens.EmailsScreen
import br.dev.locaweb_app.ui.screens.LoginScreen
import br.dev.locaweb_app.ui.screens.ProfileScreen
import br.dev.locaweb_app.ui.screens.SettingsScreen
import br.dev.locaweb_app.ui.theme.LocawebappTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LocawebappTheme {
                val navController = rememberNavController()
                Scaffold(

                    bottomBar = { MenuBar(navController = navController) }
                ) { innerPadding ->
                    // Box para garantir que a MenuBar ocupe toda a altura disponível
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding), // Adiciona o padding interno do Scaffold
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        NavGraph(navController = navController)
                    }
                }
            }
        }
    }
}



