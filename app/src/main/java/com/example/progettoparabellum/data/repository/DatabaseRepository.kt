package com.example.progettoparabellum.data.repository

import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class DatabaseRepository @Inject constructor(
    private val firestore: FirebaseFirestore
) {

}