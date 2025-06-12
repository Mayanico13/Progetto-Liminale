package com.example.progettoparabellum.ui.screen.auth.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.progettoparabellum.ui.screen.auth.TextState
import com.example.progettoparabellum.ui.screen.auth.login.LoadingScreen

@Composable
fun RegisterScreen(
    navController: NavController,
    registerViewModel: RegisterViewModel = hiltViewModel<RegisterViewModel>()
){
    val uiState by registerViewModel.uiState.collectAsState()

    when(uiState){
        is RegisterUiState.Error -> InitialScreen(registerViewModel, navController)
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
    var confirmPassword by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }

    var showPassword by remember { mutableStateOf(false) }
    var showConfirmPassword by remember { mutableStateOf(false) }

    val emailState by registerViewModel.emailState.collectAsState()
    val passwordState by registerViewModel.passwordState.collectAsState()
    val confirmPasswordState by registerViewModel.confirmPasswordState.collectAsState()
    val usernameState by registerViewModel.usernameState.collectAsState()

    Column(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.spacedBy(space = 10.dp, alignment =  Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally) {

        Text("Benvenuto!",
            color = MaterialTheme.colorScheme.onBackground)
        Text("Per continuare registrati",
            color = MaterialTheme.colorScheme.onBackground)

        OutlinedTextField(
            value = email,
            onValueChange = {email = it
                registerViewModel.onEmailChanged(it)},
            label = {Text("Email")},
            singleLine = true,
            isError = emailState == TextState.ERROR

        )
        Spacer(modifier = Modifier.offset())

        OutlinedTextField(
            value = password,
            onValueChange = {password = it
                registerViewModel.onPasswordChanged(it)},
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

        OutlinedTextField(
            value = confirmPassword,
            onValueChange = {confirmPassword = it
                            registerViewModel.onConfirmPasswordChanged(it)},
            label = {Text("Conferma Password")},
            singleLine = true,
            isError = confirmPasswordState == TextState.ERROR,
            visualTransformation = if (showConfirmPassword) {VisualTransformation.None} else {PasswordVisualTransformation()},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                val image = if (showConfirmPassword) {
                    Icons.Filled.Visibility
                } else {
                    Icons.Filled.VisibilityOff
                }
                val description = if (showConfirmPassword) {"Hide password"} else {"Show password"}
                IconButton(onClick = {showConfirmPassword = !showConfirmPassword}){
                    Icon(imageVector  = image, description)}
            }
        )

        OutlinedTextField(
            value = username,
            onValueChange = {username = it
                registerViewModel.onUsernameChange(it)},
            label = {Text("Username")},
            singleLine = true,
            isError = usernameState == TextState.ERROR,

        )

        Button(onClick = {registerViewModel.tryRegistration(email, password, confirmPassword, username)}) {
            Text("Register")
        }

        Button(onClick = {navController.navigate("Login")}) {
            Text("Login instead")
        }
    }
}
