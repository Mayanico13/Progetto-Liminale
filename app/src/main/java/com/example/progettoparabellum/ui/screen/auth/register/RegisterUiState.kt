package com.example.progettoparabellum.ui.screen.auth.register

sealed class RegisterUiState {
    object Idle : RegisterUiState()
    object Loading : RegisterUiState()
    data class Success (val message: String): RegisterUiState()
    data class Error (val message: String): RegisterUiState()
}