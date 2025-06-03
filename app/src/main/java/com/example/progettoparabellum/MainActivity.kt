package com.example.progettoparabellum

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import com.google.firebase.FirebaseApp
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.*
import com.example.progettoparabellum.ui.theme.ProgettoParabellumTheme
import com.example.progettoparabellum.ui.screen.login.LoginScreen
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        if (FirebaseApp.getApps(this).isEmpty()) {
            FirebaseApp.initializeApp(this)
        }


        setContent {
            ScreenMain()
        }
    }

    @Composable
    fun ScreenMain(){
        val navController = rememberNavController()
        ProgettoParabellumTheme {
            NavHost(navController, startDestination = Routes.Login.route) {
                composable(Routes.Login.route) {
                    LoginScreen(navController)
                }
            }
            Surface(modifier = Modifier.fillMaxSize()){
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

@HiltAndroidApp
class MyApp: Application()