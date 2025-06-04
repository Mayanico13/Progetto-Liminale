package com.example.progettoparabellum

sealed class Routes (val route: String){
    data object Login : Routes("login")
    data object Register : Routes("register")
}