package com.example.progettoparabellum.ui.screen.home.postCreation

import androidx.lifecycle.ViewModel
import com.example.progettoparabellum.data.model.Post
import com.example.progettoparabellum.data.model.UserModel
import com.example.progettoparabellum.data.repository.DatabaseRepository
import com.google.firebase.Timestamp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class PostCreationViewModel @Inject constructor(
    private val repository: DatabaseRepository
): ViewModel(){
    private val _contentState = MutableStateFlow<ContentState>(ContentState.CORRECT)
    val contentState: StateFlow<ContentState> = _contentState.asStateFlow()

    private val _creationState = MutableStateFlow<CreationState>(CreationState.Idle)
    val creationState: StateFlow<CreationState> = _creationState.asStateFlow()

    fun createPost(content: String){
        _creationState.value = CreationState.Loading
        val post = Post(UserModel.uid, content, Timestamp.now(), UserModel.username)
        repository.createPost(post){result ->
            _creationState.value = result.fold(
                onSuccess = {
                    CreationState.Success
                },
                onFailure = {
                    CreationState.Error
                }
            )
        }
    }

    fun verifyContent(content: String){
        if(content.isNotEmpty()){
            createPost(content)
        } else {
            _contentState.value = ContentState.ERROR
        }
    }

    fun onContentChange(content: String){
        _contentState.value = ContentState.CORRECT
    }
}