package com.example.progettoparabellum

import com.example.progettoparabellum.data.model.UserModel
import com.example.progettoparabellum.data.repository.DatabaseRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.User
import com.google.firebase.firestore.firestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//Tells hilt how to build repo
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    //Singleton: only one instance
    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideFireStore(): FirebaseFirestore = Firebase.firestore

    //@Provides
    //@Singleton
    //fun provideUserModel(): UserModel = TODO()
    // Ogni volta che avvi app se loggato lo pigli
}