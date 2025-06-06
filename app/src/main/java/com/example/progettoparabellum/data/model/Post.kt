package com.example.progettoparabellum.data.model

import com.google.firebase.Firebase
import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.firestore

data class Post(
    val uid: String = "",
    val content: String = "",
    val timestamp: Timestamp? = null,
    val username: String = ""
)
