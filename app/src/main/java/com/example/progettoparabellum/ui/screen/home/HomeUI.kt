package com.example.progettoparabellum.ui.screen.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.progettoparabellum.ui.screen.auth.login.LoginViewModel
import androidx.compose.foundation.lazy.items

@Composable
fun HomeScreen(
    navController: NavController,
    homeViewModel: HomeViewModel = hiltViewModel<HomeViewModel>()
){
    LazyColumn {

    }
}

@Composable
fun post(
    loginViewModel: LoginViewModel
){

}