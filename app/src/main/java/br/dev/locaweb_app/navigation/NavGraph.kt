package br.dev.locaweb_app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.dev.locaweb_app.ui.screens.EmailsScreen
import br.dev.locaweb_app.ui.screens.LoginScreen
import br.dev.locaweb_app.ui.screens.ProfileScreen
import br.dev.locaweb_app.ui.screens.SettingsScreen

@Composable
fun NavGraph(startDestination: String = "login") {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = "login") {
            LoginScreen(navController = navController)
        }
        composable(route = "profile") {
            ProfileScreen(navController = navController)
        }
        composable(route = "settings") {
            SettingsScreen(navController = navController)
        }
        composable(route = "emails") {
            EmailsScreen(navController = navController)
        }
    }
}