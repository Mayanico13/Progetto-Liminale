package com.example.progettoparabellum.ui.screen.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.progettoparabellum.ui.screen.login.InitialScreen
import com.example.progettoparabellum.ui.screen.login.LoadingScreen
import com.example.progettoparabellum.ui.screen.login.LoginTextFieldState
import com.example.progettoparabellum.ui.screen.login.LoginUiState

@Composable
fun RegisterScreen(
    navController: NavController,
    registerViewModel: RegisterViewModel = hiltViewModel<RegisterViewModel>()
){
    val uiState by registerViewModel.uiState.collectAsState()

    when(uiState){
        is RegisterUiState.Error -> TODO()
        RegisterUiState.Idle -> InitialScreen(registerViewModel, navController)
        RegisterUiState.Loading -> LoadingScreen()
        is RegisterUiState.Success -> InitialScreen(registerViewModel, navController)
    }
}

@Composable
fun InitialScreen(
    registerViewModel: RegisterViewModel,
    navController: NavController
){
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(space = 10.dp, alignment =  Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally) {

        Text("Benvenuto!")
        Text("Per continuare registrati")

        OutlinedTextField(
            value = email,
            onValueChange = {email = it},
            label = {Text("Email")},
            singleLine = true,
            isError = false

        )
        Spacer(modifier = Modifier.offset())

        OutlinedTextField(
            value = password,
            onValueChange = {password = it},
            label = {Text("Password")},
            singleLine = true,
            isError = false
        )

        OutlinedTextField(
            value = password,
            onValueChange = {password = it},
            label = {Text("Conferma Password")},
            singleLine = true,
            isError = false
        )

        Button(onClick = {registerViewModel.register(email, password)}) {
            Text("Register")
        }

        Button(onClick = {navController.navigate("Login")}) {
            Text("Login instead")
        }
    }
}
