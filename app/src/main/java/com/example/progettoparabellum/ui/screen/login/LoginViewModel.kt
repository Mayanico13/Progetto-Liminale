package com.example.progettoparabellum.ui.screen.login

import androidx.lifecycle.ViewModel
import com.example.progettoparabellum.data.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import kotlin.Result

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

    fun tryLogin(email: String, password: String){

        if(email.isNotEmpty() && password.isNotEmpty()){
            login(email, password)
        } else {
            _uiState.value = LoginUiState.Error("No vuoto")
        }
    }

    fun login(email: String, password: String){
        _uiState.value = LoginUiState.Loading
        repository.login(email, password) { result: Result<Unit> ->
            _uiState.value = result.fold(
                onSuccess = { LoginUiState.Success("")},
                onFailure = { LoginUiState.Error("Email e/o password non corrette")}

            )
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