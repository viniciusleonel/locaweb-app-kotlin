package br.dev.locaweb_app.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import br.dev.locaweb_app.navigation.NavGraph
import br.dev.locaweb_app.ui.components.CustomSnackBar
import br.dev.locaweb_app.ui.components.MenuBar
import br.dev.locaweb_app.ui.components.NavBar
import br.dev.locaweb_app.ui.components.SnackBarViewModel
import br.dev.locaweb_app.ui.components.ThemeViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun MainScreen(
    snackBarViewModel: SnackBarViewModel = viewModel(),
    themeViewModel: ThemeViewModel = viewModel()

) {
    val navController = rememberNavController()
    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val usersColor = themeViewModel.navBarColor.value

    Scaffold(
        topBar = { NavBar(navController = navController, themeViewModel = themeViewModel) },
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState) {
                CustomSnackBar(
                    snackBarHostState = snackBarHostState,
                    snackBarStatus = snackBarViewModel.snackBarState.value
                )
            }
        },
        bottomBar = { MenuBar(navController = navController, viewModel = themeViewModel) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val systemUiController = rememberSystemUiController()
            systemUiController.setStatusBarColor(color = usersColor)

            NavGraph(
                navController = navController,
                snackBarHostState = snackBarHostState,
                scope = scope,
                snackBarStatus = snackBarViewModel.snackBarState.value,
                snackBarViewModel = snackBarViewModel,
                themeViewModel = themeViewModel
            )
        }
    }

}
