package com.example.progettoparabellum.ui.screen.auth.login

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.progettoparabellum.Routes
import com.example.progettoparabellum.ui.screen.auth.TextState

@Composable
fun LoginScreen (
    navController: NavController,
    loginViewModel: LoginViewModel = hiltViewModel<LoginViewModel>()
) {

    val uiState by loginViewModel.uiState.collectAsState()


    when(uiState){
        is LoginUiState.Error -> InitialScreen(loginViewModel, navController)
        LoginUiState.Idle -> InitialScreen(loginViewModel, navController)
        LoginUiState.Loading -> LoadingScreen()
        is LoginUiState.Success -> {InitialScreen(loginViewModel, navController)
            //TOGLIERE PLZ
            Log.d("TAG", "Login effettuato? : " + loginViewModel.isUserLogged())
            //loginViewModel.logout()
            navController.navigate(Routes.Home.route)

        }
    }
}

@Composable
fun InitialScreen(
    loginViewModel: LoginViewModel,
    navController: NavController
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val emailState by loginViewModel.emailState.collectAsState()
    val passwordState by loginViewModel.passwordState.collectAsState()
    var showPassword by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.spacedBy(
            space = 10.dp,
            alignment = Alignment.CenterVertically
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text("Benvenuto!",
            color = MaterialTheme.colorScheme.onBackground)
        Text("Per continuare effettua il login",
            color = MaterialTheme.colorScheme.onBackground)

        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                loginViewModel.onEmailChanged(it)
            },
            label = { Text("Email")},
            singleLine = true,
            isError = emailState == TextState.ERROR
        )
        Spacer(modifier = Modifier.offset())

        OutlinedTextField(
            value = password,
            onValueChange = {password = it
                loginViewModel.onPasswordChanged(it)},
            label = {Text("Password")},
            singleLine = true,
            isError = passwordState == TextState.ERROR,
            visualTransformation = if (showPassword) {VisualTransformation.None} else {PasswordVisualTransformation()},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                val image = if (showPassword) {
                    Icons.Filled.Visibility
                } else {
                    Icons.Filled.VisibilityOff
                }
                val description = if (showPassword) {"Hide password"} else {"Show password"}
                IconButton(onClick = {showPassword = !showPassword}){
                    Icon(imageVector  = image, description)}
            }
        )

        Button(onClick = { loginViewModel.tryLogin(email, password) }) {
            Text("Login")
        }

        Button(onClick = { navController.navigate("register") }) {
            Text("Register instead")
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