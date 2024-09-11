package br.dev.locaweb_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import br.dev.locaweb_app.ui.theme.LocawebappTheme
import br.dev.locaweb_app.ui.theme.OceanBlue
import com.google.accompanist.systemuicontroller.rememberSystemUiController

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

@Composable
fun MainScreen(
    snackBarViewModel: SnackBarViewModel = viewModel()
) {
    val navController = rememberNavController()
    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = { NavBar(navController = navController) },
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState) { snackBarData ->
                CustomSnackBar(
                    snackBarHostState = snackBarHostState,
                    snackBarStatus = snackBarViewModel.snackBarState.value
                )
            }
        },
        bottomBar = { MenuBar(navController = navController) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val systemUiController = rememberSystemUiController()
            systemUiController.setStatusBarColor(color = OceanBlue)

            // Navegação principal
            NavGraph(
                navController = navController,
                snackBarHostState = snackBarHostState,
                scope = scope,
                snackBarStatus = snackBarViewModel.snackBarState.value,
                snackBarViewModel = snackBarViewModel
            )
        }
    }

}

