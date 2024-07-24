package com.example.chatroomapp.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class UserRepository(private val auth: FirebaseAuth,
                     private val firestore: FirebaseFirestore) {

    suspend fun singUp(
        email: String, password: String,
        firstName: String, lastName: String
    ): Result<Boolean> =

        try {
            auth.createUserWithEmailAndPassword(email, password).await()
            Result.Success(true)
        } catch (e: Exception) {
            Result.Error(e)
        }
}