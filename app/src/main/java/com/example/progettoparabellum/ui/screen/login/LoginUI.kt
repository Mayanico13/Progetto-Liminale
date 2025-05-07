package com.example.progettoparabellum.ui.screen.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.Dp
import com.example.progettoparabellum.ui.screen.login.LoginViewModel


class LoginUI {

    @Preview
    @Composable
    fun LoginFields () {
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var viewModel: LoginViewModel = viewModel()
        Column(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(space = 10.dp, alignment =  Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally) {

            Text("Benvenuto!")
            Text("Per continuare effettua il login")

            OutlinedTextField(
                value = email,
                onValueChange = {email = it},
                label = {Text("Email")},
                singleLine = true
            )
            Spacer(modifier = Modifier.offset())

            OutlinedTextField(
                value = password,
                onValueChange = {password = it},
                label = {Text("Password")},
                singleLine = true

            )

            Button(onClick = { }) {
                Text("Login")
            }
        }
    }

}