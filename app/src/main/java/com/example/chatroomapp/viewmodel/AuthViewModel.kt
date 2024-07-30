package com.example.chatroomapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatroomapp.data.Result
import com.example.chatroomapp.data.UserRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel(){

    private val userRepository : UserRepository

    init {
        userRepository = UserRepository(
            FirebaseAuth.getInstance(),
            UserRepository.Injection.instance()
        )
    }
    private val _authResult = MutableLiveData<Result<Boolean>>()
    val authResult: MutableLiveData<Result<Boolean>> get() = _authResult

    fun signUp(email: String, password: String, firstName: String, lastName: String) {
        viewModelScope.launch {
            val result = userRepository.signUp(email, password, firstName, lastName)
            _authResult.postValue(result)
        }
    }
    fun login(email: String, password: String) {
        viewModelScope.launch {
            val result = userRepository.login(email, password)
            _authResult.postValue(result)
        }
    }
}