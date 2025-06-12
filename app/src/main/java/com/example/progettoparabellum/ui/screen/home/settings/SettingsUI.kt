package com.example.progettoparabellum.ui.screen.home.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.progettoparabellum.Routes
import com.example.progettoparabellum.data.model.UserModel
import com.example.progettoparabellum.ui.screen.home.BottomBar
import com.example.progettoparabellum.ui.screen.home.HomeViewModel
import com.example.progettoparabellum.ui.screen.home.postCreation.TopBarCreation

@Composable
fun SettingsScreen(
    navController: NavController,
    homeViewModel: HomeViewModel = hiltViewModel<HomeViewModel>()
){
    Scaffold(
        topBar = { TopBarCreation(navController, "Settings") },
        bottomBar = { BottomBar(navController) }
    ) {
            innerPadding ->
        SettingsInnerBody(homeViewModel, innerPadding, navController)
    }


}


@Composable
fun SettingsInnerBody(homeViewModel: HomeViewModel, innerPadding: PaddingValues, navController: NavController){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
        .padding(innerPadding),
        verticalArrangement = Arrangement.spacedBy(space = 10.dp, alignment =  Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally){
        Text(color = MaterialTheme.colorScheme.onBackground,
            text = "Email : " + UserModel.email)
        Text(color = MaterialTheme.colorScheme.onBackground,
            text = "Username : " + UserModel.username)
        Button(onClick = { homeViewModel.logout()
        navController.navigate(Routes.Login.route)}) {
            Text("Logout")
        }
    }
}