package com.example.progettoparabellum.ui.screen.auth.register

import android.util.Patterns
import androidx.lifecycle.ViewModel
import com.example.progettoparabellum.data.repository.AuthRepository
import com.example.progettoparabellum.ui.screen.auth.TextState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<RegisterUiState>(RegisterUiState.Idle)
    val uiState: StateFlow<RegisterUiState> = _uiState.asStateFlow()

    private val _emailState = MutableStateFlow<TextState>(TextState.CORRECT)
    val emailState: StateFlow<TextState> = _emailState.asStateFlow()

    private val _passwordState = MutableStateFlow<TextState>(TextState.CORRECT)
    val passwordState: StateFlow<TextState> = _passwordState.asStateFlow()

    private val _confirmPasswordState = MutableStateFlow<TextState>(TextState.CORRECT)
    val confirmPasswordState: StateFlow<TextState> = _confirmPasswordState.asStateFlow()

    fun tryRegistration(email: String, password: String, confirmPassword: String){

        if((email.isNotEmpty() && password.isNotEmpty()) && (password == confirmPassword) && isValidEmail(email)){
            register(email, password)
        } else {
            _uiState.value = RegisterUiState.Error("No vuoto")
            _emailState.value = TextState.ERROR
            _passwordState.value = TextState.ERROR
            _confirmPasswordState.value = TextState.ERROR
        }
    }

    fun isValidEmail(target: CharSequence): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }

    fun register(email: String, password: String){
        _uiState.value = RegisterUiState.Loading

        repository.register(email, password) { result: Result<Unit> ->
            registerResult(result)
        }
    }

    fun registerResult(result: Result<Unit>){
        _uiState.value = result.fold(
            onSuccess = { RegisterUiState.Success("")},
            onFailure = { RegisterUiState.Error("BAH")}
        )

        result.onFailure { _emailState.value = TextState.ERROR
            _passwordState.value = TextState.ERROR
            _confirmPasswordState.value = TextState.ERROR
        }

    }

    fun onEmailChanged(email: String){
        _emailState.value = TextState.CORRECT
    }

    fun onPasswordChanged(password: String){
        _passwordState.value = TextState.CORRECT
    }

    fun onConfirmPasswordChanged(confirmPassword: String){
        _confirmPasswordState.value = TextState.CORRECT
    }
}