package com.example.progettoparabellum.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AuthRepository {
    private var auth: FirebaseAuth = Firebase.auth
    private val currentUser = auth.currentUser

    fun login(email: String, password: String){
        auth.signInWithEmailAndPassword(email, password)
    }
}