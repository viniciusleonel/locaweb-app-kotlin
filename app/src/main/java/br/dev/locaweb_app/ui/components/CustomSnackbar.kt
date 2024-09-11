//package br.dev.locaweb_app.ui.components
//
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Snackbar
//import androidx.compose.material3.SnackbarDuration
//import androidx.compose.material3.SnackbarHost
//import androidx.compose.material3.SnackbarHostState
//import androidx.compose.material3.Text
//import androidx.compose.material3.TextButton
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.unit.dp
//
//@Composable
//fun CustomSnackbar(
//    snackbarHostState: SnackbarHostState,
//    message: String,
//    actionLabel: String? = null,
//    actionOnClick: (() -> Unit)? = null,
//    duration: SnackbarDuration = SnackbarDuration.Short,
//    backgroundColor: Color = Color.Black,
//    textColor: Color = Color.White
//) {
//    SnackbarHost(hostState = snackbarHostState) { snackbarData ->
//        Snackbar(
//            action = {
//                actionLabel?.let {
//                    TextButton(onClick = { actionOnClick?.invoke() }) {
//                        Text(it, color = Color.Red)
//                    }
//                }
//            },
//            containerColor = backgroundColor,
//            contentColor = textColor,
//            modifier = Modifier.padding(16.dp)
//        ) {
//            Text(text = snackbarData.message)
//        }
//    }
//}