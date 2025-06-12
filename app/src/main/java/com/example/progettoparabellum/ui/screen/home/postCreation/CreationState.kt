package com.example.progettoparabellum.ui.screen.home.postCreation

sealed class CreationState {
    object Idle : CreationState()
    object Loading : CreationState()
    object Success : CreationState()
    object Error : CreationState()
}