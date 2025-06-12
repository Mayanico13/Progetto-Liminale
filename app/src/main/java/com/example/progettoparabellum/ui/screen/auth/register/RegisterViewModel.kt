package com.example.progettoparabellum.ui.screen.auth.register

import android.util.Patterns
import androidx.compose.material3.Text
import androidx.lifecycle.ViewModel
import com.example.progettoparabellum.data.model.UserModel
import com.example.progettoparabellum.data.repository.AuthRepository
import com.example.progettoparabellum.data.repository.DatabaseRepository
import com.example.progettoparabellum.ui.screen.auth.TextState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repository: AuthRepository,
    private val dataRepo: DatabaseRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<RegisterUiState>(RegisterUiState.Idle)
    val uiState: StateFlow<RegisterUiState> = _uiState.asStateFlow()

    private val _emailState = MutableStateFlow<TextState>(TextState.CORRECT)
    val emailState: StateFlow<TextState> = _emailState.asStateFlow()

    private val _passwordState = MutableStateFlow<TextState>(TextState.CORRECT)
    val passwordState: StateFlow<TextState> = _passwordState.asStateFlow()

    private val _confirmPasswordState = MutableStateFlow<TextState>(TextState.CORRECT)
    val confirmPasswordState: StateFlow<TextState> = _confirmPasswordState.asStateFlow()

    private val _usernameState = MutableStateFlow<TextState>(TextState.CORRECT)
    val usernameState: StateFlow<TextState> = _usernameState.asStateFlow()

    fun tryRegistration(email: String, password: String, confirmPassword: String, username: String){

        if((email.isNotEmpty() && password.isNotEmpty()) && (password == confirmPassword) && isValidEmail(email) && username.isNotEmpty()){
            register(email, password, username)
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

    fun register(email: String, password: String, username: String){
        _uiState.value = RegisterUiState.Loading
        repository.register(email, password) { result: Result<Unit> ->


            result.onSuccess {
                _uiState.value = RegisterUiState.Success("")
                UserModel.uid = repository.getUid()!!
                UserModel.email = email
                UserModel.username = username
                dataRepo.createUser()
            }

            result.onFailure {
                _uiState.value = RegisterUiState.Error("BAH")
                _emailState.value = TextState.ERROR
                _passwordState.value = TextState.ERROR
                _confirmPasswordState.value = TextState.ERROR
                _usernameState.value = TextState.ERROR
            }
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

    fun onUsernameChange(username: String){
        _usernameState.value = TextState.CORRECT
    }
}