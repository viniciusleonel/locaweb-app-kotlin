package br.dev.locaweb_app.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.dev.locaweb_app.model.user.UserViewModel
import br.dev.locaweb_app.ui.components.SnackBarStatus
import br.dev.locaweb_app.ui.components.SnackBarViewModel
import br.dev.locaweb_app.ui.components.ThemeViewModel
import br.dev.locaweb_app.ui.screens.EditProfileScreen
import br.dev.locaweb_app.ui.screens.EmailsScreen
import br.dev.locaweb_app.ui.screens.LoginScreen
import br.dev.locaweb_app.ui.screens.RegisterScreen
import br.dev.locaweb_app.ui.screens.SettingsScreen
import br.dev.locaweb_app.ui.screens.ThemeScreen
import br.dev.locaweb_app.ui.theme.darkenColor
import kotlinx.coroutines.CoroutineScope

@Composable
fun NavGraph(
    navController: NavHostController,
    userViewModel: UserViewModel = viewModel(),
    snackBarViewModel: SnackBarViewModel,
    snackBarHostState: SnackbarHostState,
    snackBarStatus: SnackBarStatus,
    scope: CoroutineScope,
    themeViewModel: ThemeViewModel,
    startDestination: String = "login"
) {
    NavHost(navController = navController, startDestination = startDestination) {
        val usersColor = themeViewModel.navBarColor.value
        val darkNavBarColor = darkenColor(usersColor, 0.4f)
        val buttonColors = listOf(darkNavBarColor, usersColor, darkNavBarColor)
        composable(route = "login") {
            LoginScreen(
                navController = navController,
                snackBarViewModel = snackBarViewModel,
                snackBarHostState = snackBarHostState,
                scope = scope,
                userViewModel = userViewModel,
                themeViewModel = themeViewModel,
                buttonColors = buttonColors
            )
        }
        composable(route = "register") {
            RegisterScreen(
                navController = navController,
                snackBarHostState = snackBarHostState,
                snackBarViewModel = snackBarViewModel,
                scope = scope,
                themeViewModel = themeViewModel,
                buttonColors = buttonColors
            )
        }
//        composable(route = "profile") {
//            ProfileScreen(
//                navController = navController,
//                snackBarHostState = snackBarHostState,
//                snackBarViewModel = snackBarViewModel,
//                scope = scope,
//                userViewModel = userViewModel
//            )
//        }
        composable(route = "edit-profile") {
            EditProfileScreen(
                navController = navController,
                snackBarHostState = snackBarHostState,
                snackBarViewModel = snackBarViewModel,
                scope = scope,
                userViewModel = userViewModel,
                themeViewModel = themeViewModel,
                buttonColors = buttonColors
            )
        }
        composable(route = "settings") {
            SettingsScreen(
                navController = navController,
                buttonColors = buttonColors,
                themeViewModel = themeViewModel,
            )
        }
        composable(route = "theme") {
            ThemeScreen(
                userViewModel = userViewModel,
                themeViewModel = themeViewModel,
                navController = navController,
                buttonColors = buttonColors,
                snackBarHostState = snackBarHostState,
                snackBarViewModel = snackBarViewModel,
                scope = scope,
            )
        }
        composable(route = "emails") {
            EmailsScreen(
                navController = navController,
                buttonColors = buttonColors,
                themeViewModel = themeViewModel,
            )
        }
    }
}
