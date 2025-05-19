package com.example.progettoparabellum.ui.screen.login

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.progettoparabellum.data.LoginRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel (
    private val repository: LoginRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<LoginUiState>(LoginUiState.Idle)
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()



    fun login(email: String, password: String){
        _uiState.value = LoginUiState.Loading
        try {
            //login
        } catch (Exception e){
            _uiState.value = LoginUiState.Error(message = "Error")
        }

        //smt

        _uiState.value = LoginUiState.Success("Bob")
    }

}