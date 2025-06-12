package com.example.progettoparabellum

import android.app.Application
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
import com.example.progettoparabellum.ui.screen.auth.login.LoginScreen
import com.example.progettoparabellum.ui.screen.auth.register.RegisterScreen
import com.example.progettoparabellum.ui.screen.home.HomeScreen
import com.example.progettoparabellum.ui.screen.home.postCreation.PostCreateScreen
import com.example.progettoparabellum.ui.screen.home.settings.SettingsScreen
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
        val authRepo = AppModule.provideFirebaseAuth()
        val dataRepo = AppModule.provideFireStore()
        var route : String
        if(authRepo.currentUser != null){
            route = Routes.Home.route
        } else {
            route = Routes.Login.route
        }

        ProgettoParabellumTheme {
            NavHost(navController, startDestination = route) {
                composable(Routes.Login.route) {
                    LoginScreen(navController)
                }

                composable(Routes.Register.route) {
                    RegisterScreen(navController)
                }

                composable(Routes.Home.route) {
                    HomeScreen(navController)
                }

                composable(Routes.CreatePost.route){
                    PostCreateScreen(navController)
                }

                composable(Routes.Settings.route){
                    SettingsScreen(navController)
                }
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