package com.example.progettoparabellum

import com.google.firebase.auth.FirebaseAuth
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
}