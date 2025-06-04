package com.example.progettoparabellum.ui.screen.register

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
class RegisterViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<RegisterUiState>(RegisterUiState.Idle)
    val uiState: StateFlow<RegisterUiState> = _uiState.asStateFlow()

    fun register(email: String, password: String){
        _uiState.value = RegisterUiState.Loading

        viewModelScope.launch {
            try {
                repository.register(email, password)
            } catch (e: Exception){
                _uiState.value = RegisterUiState.Error("Email e/o password non corrette")
            }
            _uiState.value = RegisterUiState.Success("")
        }
    }
}