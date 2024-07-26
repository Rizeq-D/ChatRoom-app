package com.example.chatroomapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.chatroomapp.data.UserRepository
import com.google.firebase.auth.FirebaseAuth

class AuthViewModel : ViewModel(){

    private val userRepository : UserRepository

    init {
        userRepository = UserRepository(
            FirebaseAuth.getInstance(),
            UserRepository.Injection.instance()
        )
    }
}