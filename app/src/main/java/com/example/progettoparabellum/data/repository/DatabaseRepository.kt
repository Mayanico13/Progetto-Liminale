package com.example.progettoparabellum.data.repository

import android.util.Log
import com.example.progettoparabellum.data.model.Post
import com.example.progettoparabellum.data.model.TempUser
import com.example.progettoparabellum.data.model.UserModel
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.auth.User
import javax.inject.Inject

class DatabaseRepository @Inject constructor(
    private val firestore: FirebaseFirestore
) {
    fun getPosts(callback: (List<Post>) -> Unit){
        var postList: List<Post> = emptyList()
        firestore.collection("posts").orderBy("timestamp", Query.Direction.DESCENDING).get().addOnSuccessListener {

            result ->  Log.d("TAG", result.toString())
            postList = result.documents.mapNotNull { it.toObject(Post::class.java) }
            callback(postList)
        }.addOnFailureListener {
            callback(emptyList())
        }

    }



    fun getUser(uid: String, callback: (TempUser) -> Unit){
        firestore.collection("users").document(uid).get().addOnSuccessListener{
            result ->
            val user: TempUser = result.toObject(TempUser::class.java)!!
            callback(user)
        }
    }

    fun createUser(){
        val docRef = firestore.collection("users").document(UserModel.uid)
        docRef.set(UserModel).addOnSuccessListener {
            Log.d("TAG", "User added to repo")
        }
    }

    fun createPost(post: Post, callback: (Result<Unit>) -> Unit){
        val docRef = firestore.collection("posts").document()
        docRef.set(post).addOnSuccessListener {
            callback(Result.success(Unit))
        }.addOnFailureListener {
            callback(Result.failure(Exception("")))
        }
    }

}


