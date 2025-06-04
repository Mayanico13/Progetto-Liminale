package com.example.progettoparabellum.ui.screen.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.progettoparabellum.data.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<LoginUiState>(LoginUiState.Idle)
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    private val _emailState = MutableStateFlow<LoginTextFieldState>(LoginTextFieldState.Correct)
    val emailState: StateFlow<LoginTextFieldState> = _emailState.asStateFlow()

    private val _passwordState = MutableStateFlow<LoginTextFieldState>(LoginTextFieldState.Correct)
    val passwordState: StateFlow<LoginTextFieldState> = _passwordState.asStateFlow()



    fun login(email: String, password: String){
        _uiState.value = LoginUiState.Loading

        viewModelScope.launch {
            try {
                repository.login(email, password)
            } catch (e: Exception){
                _uiState.value = LoginUiState.Error("Email e/o password non corrette")
                _passwordState.value = LoginTextFieldState.Error
                _emailState.value = LoginTextFieldState.Error
            }
            _uiState.value = LoginUiState.Success("")
        }
    }

    /*@AssistedFactory
    interface MyViewModelFactory {
        fun create(repository: AuthRepository): LoginViewModel
    }*/
}


/*class LoginViewModelFactory(
    private val repository: AuthRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel(repository) as T
    }
}*/