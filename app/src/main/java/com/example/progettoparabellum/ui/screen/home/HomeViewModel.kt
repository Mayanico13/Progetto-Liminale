package com.example.progettoparabellum.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.progettoparabellum.data.model.Post
import com.example.progettoparabellum.data.model.UserModel
import com.example.progettoparabellum.data.repository.DatabaseRepository
import com.example.progettoparabellum.ui.screen.auth.login.LoginUiState
import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.auth.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: DatabaseRepository
    //private val user: UserModel
): ViewModel() {
    private val _postList = MutableStateFlow<List<Post>>(emptyList())
    val postList: StateFlow<List<Post>> = _postList.asStateFlow()

    private val _homeState = MutableStateFlow<HomeState>(HomeState.Idle)
    val homeState: StateFlow<HomeState> = _homeState.asStateFlow()

    fun loadPost(){
        _homeState.value = HomeState.Loading
         repository.getPosts() { result ->
             if(result.isNotEmpty()){
                 _homeState.value = HomeState.Idle
                 _postList.value = result
             } else {
                 _homeState.value = HomeState.Idle
             }
         }
    }

    fun createPost(content: String){

        if(content.isNotEmpty()) {
            val post: Post = Post("user.uid", content, Timestamp.now(), "user.username")
            repository.createPost(post)
        } else {
            TODO()
        }
    }



}