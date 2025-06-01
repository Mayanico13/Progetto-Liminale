package com.example.progettoparabellum

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.google.firebase.FirebaseApp
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.*
import com.example.progettoparabellum.ui.theme.ProgettoParabellumTheme
import com.example.progettoparabellum.ui.screen.login.LoginScreen



class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        if (FirebaseApp.getApps(this).isEmpty()) {
            FirebaseApp.initializeApp(this)
        }


        setContent {
            ProgettoParabellumTheme {
                ScreenMain()
            }
        }
    }

    @Composable
    fun ScreenMain(){
        val navController = rememberNavController()
        NavHost(navController, startDestination = Routes.Login.route){
            composable(Routes.Login.route){
                LoginScreen(navController)
            }
        }
    }


}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ProgettoParabellumTheme {
        Greeting("Android")
    }
}