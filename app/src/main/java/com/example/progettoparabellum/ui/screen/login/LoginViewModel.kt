package com.example.progettoparabellum.ui.screen.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.progettoparabellum.data.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<LoginUiState>(LoginUiState.Idle)
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()



    fun login(email: String, password: String){
        _uiState.value = LoginUiState.Loading

        viewModelScope.launch {
            try {
                repository.login(email, password)
            } catch (e: Exception){
                _uiState.value = LoginUiState.Error("Email e/o password non corrette")
            }
            _uiState.value = LoginUiState.Success("")
        }




    }

}