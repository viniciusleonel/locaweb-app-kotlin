package br.dev.locaweb_app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import br.dev.locaweb_app.ui.theme.Blue
import br.dev.locaweb_app.ui.theme.NavColor
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun NavBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val systemUiController = rememberSystemUiController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    if (currentRoute != "login" && currentRoute != "register") {

        systemUiController.setStatusBarColor(
            color = Blue
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .background(color = NavColor),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (currentRoute != null) {
                Text(
                    text = currentRoute.uppercase(),
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White
                )
            } else {
                Text(
                    text = "Tittle",
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
    }


}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun NavBarPrev() {
    val navController = rememberNavController()
    NavBar(navController)
}
