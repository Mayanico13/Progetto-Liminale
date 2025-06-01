package com.example.progettoparabellum.ui.screen.login

import android.R
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

    @Composable
    fun LoginScreen (

        navController: NavController
    ) {
        val loginViewModel: LoginViewModel = viewModel()
        val loginUiState by loginViewModel.uiState.collectAsState()


        when(val state = loginUiState){
            is LoginUiState.Error -> InitialScreen(loginViewModel, true)
            LoginUiState.Idle -> InitialScreen(loginViewModel, false)
            LoginUiState.Loading -> LoadingScreen()
            is LoginUiState.Success -> InitialScreen(loginViewModel, false)
        }
    }

    @Composable
    fun InitialScreen(
        loginViewModel: LoginViewModel,
        isError: Boolean
    ){
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
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
                isError = isError

            )
            Spacer(modifier = Modifier.offset())

            OutlinedTextField(
                value = password,
                onValueChange = {password = it},
                label = {Text("Password")},
                singleLine = true,
                isError = isError
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

