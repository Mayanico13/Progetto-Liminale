package com.example.progettoparabellum.ui.screen.login

import androidx.lifecycle.ViewModel
import com.example.progettoparabellum.data.LoginRepository

class LoginViewModel (
    private val repository: LoginRepository
) : ViewModel() {
    var email: String = ""
    var password: String = ""



    fun loginButton(){

    }

    companion object{

    }
}