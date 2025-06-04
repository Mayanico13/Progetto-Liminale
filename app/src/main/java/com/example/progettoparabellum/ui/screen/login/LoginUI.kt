package com.example.progettoparabellum.ui.screen.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.hilt.navigation.compose.hiltViewModel

    @Composable
    fun LoginScreen (
        navController: NavController,
        loginViewModel: LoginViewModel = hiltViewModel()
    ) {

        val loginUiState by loginViewModel.uiState.collectAsState()


        when(loginUiState){
            is LoginUiState.Error -> TODO()
            LoginUiState.Idle -> InitialScreen(loginViewModel)
            LoginUiState.Loading -> LoadingScreen()
            is LoginUiState.Success -> InitialScreen(loginViewModel)
        }
    }

    @Composable
    fun InitialScreen(
        loginViewModel: LoginViewModel
    ){
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        val emailState by loginViewModel.emailState.collectAsState()
        val passwordState by loginViewModel.passwordState.collectAsState()

        var isEmailCorrect: Boolean = when(emailState){
            LoginTextFieldState.Correct -> true
            LoginTextFieldState.Error -> false
        }

        var isPasswordCorrect: Boolean = when(passwordState){
            LoginTextFieldState.Correct -> true
            LoginTextFieldState.Error -> false
        }

        Column(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(space = 10.dp, alignment =  Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally) {

            Text("Benvenuto!")
            Text("Per continuare effettua il login")

            OutlinedTextField(
                value = email,
                onValueChange = {email = it},
                label = {Text("Email")},
                singleLine = true,
                isError = !isEmailCorrect

            )
            Spacer(modifier = Modifier.offset())

            OutlinedTextField(
                value = password,
                onValueChange = {password = it},
                label = {Text("Password")},
                singleLine = true,
                isError = !isPasswordCorrect
            )

            Button(onClick = {loginViewModel.login(email, password)}) {
                Text("Login")
            }
        }
    }

    @Composable
    fun LoadingScreen(){
        Box (
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            CircularProgressIndicator(
                modifier = Modifier.size(64.dp)
            )
        }
    }


