package br.dev.locaweb_app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.dev.locaweb_app.ui.theme.Blue

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    navController: NavController
    ) {
    Column (
        modifier = Modifier
            .fillMaxSize()
    ) {

        Row (
            modifier = Modifier
                .height(150.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(bottomStart = 30.dp, bottomEnd = 30.dp))
                .background(color = Blue)
        ){
//            Text(
//                text = "Profile",
//                style = MaterialTheme.typography.titleLarge)
        }
//        Button(
//            onClick = {
//                navController.navigate("settings")
//            },
//            colors = ButtonDefaults.buttonColors(Color.Cyan),
//        ) {
//
//        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ProfileScreenPrev() {
    val navController = rememberNavController()
    ProfileScreen(navController = navController)

}