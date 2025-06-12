package com.example.progettoparabellum

sealed class Routes (val route: String){
    data object Login : Routes("login")
    data object Register : Routes("register")
    data object Home : Routes("home")
    data object CreatePost : Routes("createPost")
    data object Settings : Routes("settings")
}