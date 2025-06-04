package com.example.progettoparabellum.data

import com.google.firebase.auth.FirebaseAuth
import jakarta.inject.Inject
import kotlin.Result

class AuthRepository @Inject constructor(
    private val auth : FirebaseAuth
){
    private var currentUser = auth.currentUser

    fun isLogged() : Boolean{
        return (auth.currentUser != null)
    }

    fun login(email: String, password: String, callback: (Result<Unit>) -> Unit) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                currentUser = auth.currentUser
                callback(Result.success(Unit))

            } else {
                callback(Result.failure(Exception("")))

            }
        }
    }
    fun register(email: String, password: String){
        TODO()
    }





}