package br.dev.locaweb_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.dev.locaweb_app.ui.theme.LocawebappTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LocawebappTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    // Box para garantir que a MenuBar ocupe toda a altura disponível
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding) // Adiciona o padding interno do Scaffold
                    ) {
                        MenuBar(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(bottom = 70.dp) // Garante que a MenuBar não sobreponha o menu inferior
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        color = Color.Red,
        modifier = modifier
    )
}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun GreetingPreview() {
//    LocawebappTheme {
//        Greeting("Android")
//    }
//}

@Composable
fun MenuItem(icon: ImageVector, text: String, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Column(
        modifier = modifier
            .clickable(onClick = onClick)
            .padding(8.dp), // Adiciona um padding para tornar a área clicável maior
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = icon,
            contentDescription = text,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = text)
    }
}

@Composable
fun DividerSpacer() {
    Box(
        modifier = Modifier
            .width(1.dp) // Largura do traço
            .fillMaxHeight() // Ocupa a altura disponível
            .background(Color.Gray) // Cor do traço
    )
}

@Composable
fun MenuBar(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
//            .background(Color.Gray)

    ) {
        Spacer(modifier = Modifier
            .weight(1f)) // Adiciona um espaçador para empurrar a Row para baixo
        Box(
            modifier = Modifier
                .border(1.dp, Color.Gray)

        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly, // Espaço entre os itens
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth() // Garante que a Row ocupe toda a largura
                    .height(80.dp) // Altura fixa do traço
            ) {
                MenuItem(
                    icon = Icons.Filled.Settings,
                    text = "Settings",
                    modifier = Modifier.weight(1f), // Faz com que o MenuItem ocupe toda a largura disponível
                    onClick = {
                        // Ação a ser executada quando o botão for clicado
                        println("Settings button clicked")
                    }
                )
                DividerSpacer()
                MenuItem(
                    icon = Icons.Filled.Email,
                    text = "Emails",
                    modifier = Modifier.weight(1f),
                    onClick = {
                        // Ação a ser executada quando o botão for clicado
                        println("Emails button clicked")
                    }
                )
                DividerSpacer()
                MenuItem(
                    icon = Icons.Filled.Person,
                    text = "Profile",
                    modifier = Modifier.weight(1f),
                    onClick = {
                        // Ação a ser executada quando o botão for clicado
                        println("Profile button clicked")
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun MenuBarPrev() {
    MenuBar()
}
