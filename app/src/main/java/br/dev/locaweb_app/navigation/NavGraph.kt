package br.dev.locaweb_app.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.dev.locaweb_app.model.user.UserViewModel
import br.dev.locaweb_app.ui.screens.EmailsScreen
import br.dev.locaweb_app.ui.screens.LoginScreen
import br.dev.locaweb_app.ui.screens.ProfileScreen
import br.dev.locaweb_app.ui.screens.RegisterScreen
import br.dev.locaweb_app.ui.screens.SettingsScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    userViewModel: UserViewModel = viewModel(),
    startDestination: String = "login") {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = "login") {
            LoginScreen(
                navController = navController,
                userViewModel = userViewModel
                )
        }
        composable(route = "register") {
            RegisterScreen(
                navController = navController,
//                userViewModel = userViewModel
                )
        }
        composable(route = "profile") {
            ProfileScreen(
                navController = navController,
                userViewModel = userViewModel
                )
        }
        composable(route = "settings") {
            SettingsScreen(navController = navController)
        }
        composable(route = "emails") {
            EmailsScreen(navController = navController)
        }
    }
}