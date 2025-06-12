package com.example.progettoparabellum.ui.screen.home

sealed class HomeState {
    object Idle : HomeState()
    object Loading : HomeState()
}