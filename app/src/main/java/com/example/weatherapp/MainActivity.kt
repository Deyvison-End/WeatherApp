package com.example.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.ui.HomePage
import com.example.weatherapp.ui.nav.BottomNavBar
import com.example.weatherapp.ui.nav.BottomNavItem
import com.example.weatherapp.ui.nav.MainNavHost
import com.example.weatherapp.ui.theme.WeatherAppTheme

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            WeatherAppTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("Bem-vindo/a!") },
                            actions = {
                                IconButton(onClick = { finish() }) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                                        contentDescription = "Sair do Aplicativo"
                                    )
                                }
                            }
                        )
                    },
                    bottomBar = {
                        // Lista com os botões que criamos na Parte 2
                        val items = listOf(
                            BottomNavItem.HomeButton,
                            BottomNavItem.ListButton,
                            BottomNavItem.MapButton
                        )
                        // Renderiza a nossa barra inferior personalizada
                        BottomNavBar(navController = navController, items = items)
                    },
                    floatingActionButton = {
                        FloatingActionButton(onClick = { /* Ação futura */ }) {
                            Icon(Icons.Default.Add, contentDescription = "Adicionar")
                        }
                    }
                ) { innerPadding ->
                    // O Box garante que o conteúdo das páginas respeite as barras topo/inferior
                    Box(modifier = Modifier.padding(innerPadding)) {
                        MainNavHost(navController = navController)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WeatherAppTheme {
        HomePage()
    }
}