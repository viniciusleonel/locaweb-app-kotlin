package br.dev.locaweb_app.utils

import androidx.navigation.NavController

fun reloadScreen(navController: NavController, route: String) {
    navController.popBackStack()
    navController.navigate(route)

}