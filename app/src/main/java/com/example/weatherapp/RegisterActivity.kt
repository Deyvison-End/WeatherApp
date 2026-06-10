package com.example.weatherapp


import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherapp.ui.theme.WeatherAppTheme
import android.widget.Toast
import androidx.compose.foundation.layout.Row
import com.google.firebase.Firebase
import com.google.firebase.auth.auth


class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    RegisterPage(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}



@Composable
fun RegisterPage(modifier: Modifier = Modifier) {

    val activity = LocalContext.current as Activity

    var nome by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var senha by rememberSaveable { mutableStateOf("") }
    var repetirSenha by rememberSaveable { mutableStateOf("") }

    val formularioValido =
        nome.isNotEmpty() &&
                email.isNotEmpty() &&
                senha.isNotEmpty() &&
                repetirSenha.isNotEmpty() &&
                senha == repetirSenha

    Column(
        modifier = modifier
            .padding(24.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        OutlinedTextField(
            value = nome,
            onValueChange = { nome = it },
            label = { Text("Nome") }
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("E-mail") }
        )

        OutlinedTextField(
            value = senha,
            onValueChange = { senha = it },
            label = { Text("Senha") },
            visualTransformation = PasswordVisualTransformation()
        )

        OutlinedTextField(
            value = repetirSenha,
            onValueChange = { repetirSenha = it },
            label = { Text("Repetir senha") },
            visualTransformation = PasswordVisualTransformation()
        )

        Row(
            modifier = Modifier.padding(top = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            Button(
                onClick = {

                    Firebase.auth
                        .createUserWithEmailAndPassword(email, senha)
                        .addOnCompleteListener(activity) { task ->

                            if (task.isSuccessful) {

                                Toast.makeText(
                                    activity,
                                    "Registro OK!",
                                    Toast.LENGTH_LONG
                                ).show()

                                activity.finish()

                            } else {

                                Toast.makeText(
                                    activity,
                                    task.exception?.message ?: "Registro FALHOU!",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                },
                enabled = formularioValido
            ) {
                Text("Registrar")
            }

            Button(
                onClick = {
                    nome = ""
                    email = ""
                    senha = ""
                    repetirSenha = ""
                }
            ) {
                Text("Limpar")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    WeatherAppTheme {
        RegisterPage()
    }
}