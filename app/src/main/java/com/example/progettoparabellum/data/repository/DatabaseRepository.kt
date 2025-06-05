package com.example.progettoparabellum.data.repository

import android.util.Log
import com.example.progettoparabellum.data.model.Post
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
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

    }
