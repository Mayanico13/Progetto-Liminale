package com.example.progettoparabellum.data.model

import com.google.firebase.Timestamp

data class PostModel(
    val uid: String = "",
    val post: String = "",
    val timestamp: Timestamp? = null
)
