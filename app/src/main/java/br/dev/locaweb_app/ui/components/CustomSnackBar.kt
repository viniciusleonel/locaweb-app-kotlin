package br.dev.locaweb_app.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CustomSnackBar(
    snackBarHostState: SnackbarHostState,
    snackBarStatus: SnackBarStatus,
) {

    val containerColor: Color
    val contentColor: Color
    val icon: androidx.compose.ui.graphics.vector.ImageVector
    val iconColor: Color

    when (snackBarStatus) {
        SnackBarStatus.SUCCESS -> {
            containerColor = Color(0xFF104C12)
            contentColor = Color.White
            icon = Icons.Outlined.CheckCircle
            iconColor = Color(0xFF4CAF50)
        }
        SnackBarStatus.ERROR -> {
            containerColor = Color(0xFFDD493E)
            contentColor = Color.White
            icon = Icons.Outlined.Warning
            iconColor = Color.White
        }
        SnackBarStatus.NONE -> {
            // Nenhum estado de Snackbar a exibir
            return
        }
    }

    SnackbarHost(
        modifier = Modifier.padding(15.dp),
        hostState = snackBarHostState,
        snackbar = {
            Snackbar (
                containerColor = containerColor,
                contentColor = contentColor,
                shape = RoundedCornerShape(15.dp),
                dismissAction = {
                    IconButton(onClick = {
                        snackBarHostState.currentSnackbarData?.dismiss()
                    }) {
                        Icon(
                            imageVector = Icons.Outlined.Close,
                            contentDescription = null,
                            tint = Color.White,
                        )
                    }
                }
            ){
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ){
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = iconColor,
                        modifier = Modifier.padding(end = 15.dp)
                    )

                    Text(
                        text = snackBarHostState.currentSnackbarData?.visuals?.message.toString(),
                        modifier = Modifier.padding(top = 2.dp)
                        )
                }
            }
        }
    )
}