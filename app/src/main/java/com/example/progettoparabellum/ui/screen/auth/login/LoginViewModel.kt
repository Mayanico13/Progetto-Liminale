package com.example.progettoparabellum.ui.screen.auth.login

import android.util.Patterns
import androidx.lifecycle.ViewModel
import com.example.progettoparabellum.data.repository.AuthRepository
import com.example.progettoparabellum.data.repository.DatabaseRepository
import com.example.progettoparabellum.ui.screen.auth.TextState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import kotlin.Result

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: AuthRepository,
    private val dataRepo: DatabaseRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<LoginUiState>(LoginUiState.Idle)
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    private val _emailState = MutableStateFlow<TextState>(TextState.CORRECT)
    val emailState: StateFlow<TextState> = _emailState.asStateFlow()

    private val _passwordState = MutableStateFlow<TextState>(TextState.CORRECT)
    val passwordState: StateFlow<TextState> = _passwordState.asStateFlow()

    fun tryLogin(email: String, password: String){

        if(email.isNotEmpty() && password.isNotEmpty() && isValidEmail(email)){
                login(email, password)
        } else {
            _uiState.value = LoginUiState.Error("No vuoto")
            _emailState.value = TextState.ERROR
            _passwordState.value = TextState.ERROR
        }
    }

    fun isValidEmail(target: CharSequence): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }

    fun login(email: String, password: String){
        _uiState.value = LoginUiState.Loading
        repository.login(email, password) { result: Result<Unit> ->
            loginResult(result)
        }

    }

    fun loginResult(result: Result<Unit>){
        _uiState.value = result.fold(
            onSuccess = {
                LoginUiState.Success("")},
            onFailure = { LoginUiState.Error("Email e/o password non corrette")}
        )

        result.onFailure { _emailState.value = TextState.ERROR
            _passwordState.value = TextState.ERROR
        }

    }

    fun isUserLogged() : Boolean{
        return repository.isLogged()
    }

    fun logout(){
        repository.logout()
    }

    fun onEmailChanged(email: String){
        _emailState.value = TextState.CORRECT
    }

    fun onPasswordChanged(password: String){
        _passwordState.value = TextState.CORRECT
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