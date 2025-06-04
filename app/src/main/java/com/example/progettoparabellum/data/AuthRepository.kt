package com.example.progettoparabellum.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import jakarta.inject.Inject

class AuthRepository @Inject constructor(
    private val auth : FirebaseAuth
){
    private val currentUser = auth.currentUser

    fun isLogged() : Boolean{
        return (currentUser != null)
    }

    fun login(email: String, password: String){
        try{
            auth.signInWithEmailAndPassword(email, password)
        }catch(e: Error){
          throw error("")
        }
    }




}