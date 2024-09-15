package br.dev.locaweb_app.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.dev.locaweb_app.ui.components.ShowColorPicker
import br.dev.locaweb_app.ui.components.ThemeViewModel
import br.dev.locaweb_app.ui.theme.darkenColor
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun ThemeScreen(
    modifier: Modifier = Modifier,
    themeViewModel: ThemeViewModel,
    buttonColors: List<Color>? = null
) {
    val systemUiController = rememberSystemUiController()
    val usersColor = themeViewModel.navBarColor.value

    LaunchedEffect(usersColor) {
        systemUiController.setStatusBarColor(color = usersColor)
    }

    val isDarkTheme = themeViewModel.isDarkTheme.value
    val darkNavBarColor = darkenColor(usersColor, 0.4f)

    Column(
        modifier = modifier
            .padding(20.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Choose your theme:",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Current theme: " + if (isDarkTheme) "Dark" else "Light",
                fontSize = 18.sp,
            )

            Switch(
                checked = isDarkTheme,
                onCheckedChange = { themeViewModel.toggleTheme() },
                colors = SwitchDefaults.colors(
                    checkedThumbColor =  usersColor,
                    checkedTrackColor = darkNavBarColor,
                    uncheckedTrackColor = Color.LightGray,
                    uncheckedThumbColor = darkNavBarColor,
                    uncheckedBorderColor = Color.Gray
                )
            )
        }
        Spacer(modifier = Modifier.padding(0.dp, 5.dp))
        HorizontalDivider(
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
        )
        Spacer(modifier = Modifier.height(16.dp))
        ShowColorPicker(themeViewModel, buttonColors)
        Spacer(modifier = Modifier.height(16.dp))
        HorizontalDivider(
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}
