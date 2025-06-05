package com.example.progettoparabellum.ui.screen.home

import androidx.lifecycle.ViewModel
import com.example.progettoparabellum.data.repository.DatabaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: DatabaseRepository
): ViewModel() {

}