package com.example.progettoparabellum.data.model

import com.google.firebase.Timestamp

data class Post(
    val uid: String = "",
    val content: String = "",
    val timestamp: Timestamp? = null
)
