package br.dev.locaweb_app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp

@Composable
fun EmailMenu(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    onReceivedEmailsClick: () -> Unit,
    onSentEmailsClick: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = modifier,

        ) {
        Icon(
            modifier = Modifier
                .padding(end = 16.dp)
                .clickable { expanded = true },
            imageVector = Icons.Filled.Menu,
            contentDescription = "",
            tint = Color.White,
        )

        DropdownMenu(
            modifier = modifier
                .background(backgroundColor.copy(alpha = 0.7f)),

            expanded = expanded,
            onDismissRequest = { expanded = false },
            offset = DpOffset(x = 0.dp, y = 23.dp)
        ) {
            DropdownMenuItem(
                text = { Text("Received Emails") },
                onClick = {
                    expanded = false
                    onReceivedEmailsClick()
                }
            )

            DropdownMenuItem(
                text = { Text("Sent Emails") },
                onClick = {
                    expanded = false
                    onSentEmailsClick()
                }
            )
        }
    }
}



