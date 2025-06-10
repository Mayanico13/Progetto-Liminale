package com.example.progettoparabellum.data.repository

import android.util.Log
import com.example.progettoparabellum.data.model.Post
import com.example.progettoparabellum.data.model.UserModel
import com.google.firebase.firestore.DocumentReference
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

    fun addUser(user: UserModel){
        firestore.collection("users").add(TODO())
    }

    fun getUser(uid: String, callback: (UserModel?) -> Unit){
        firestore.collection("users").document(uid).get().addOnSuccessListener{
            result ->
            var user = result.toObject(UserModel::class.java)
            callback(user)
        }.addOnFailureListener {
            callback(null)
        }
    }

    fun createPost(post: Post){
        firestore.collection("posts").add(post).addOnSuccessListener {
            TODO()
        }.addOnFailureListener {
            TODO()
        }
    }

}


