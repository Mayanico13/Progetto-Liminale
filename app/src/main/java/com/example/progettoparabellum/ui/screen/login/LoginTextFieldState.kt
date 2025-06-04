package com.example.progettoparabellum.ui.screen.login

sealed class LoginTextFieldState {
    object Correct: LoginTextFieldState()
    object Error: LoginTextFieldState()

}