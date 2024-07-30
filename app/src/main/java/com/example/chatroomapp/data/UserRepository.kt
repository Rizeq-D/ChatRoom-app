package com.example.chatroomapp.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class UserRepository(private val auth: FirebaseAuth,
                     private val firestore: FirebaseFirestore) {

    suspend fun signUp(email: String, password: String, firstName: String, lastName: String): Result<Boolean> =
        try {
            auth.createUserWithEmailAndPassword(email, password).await()
            val user = User(firstName, lastName, email)
            saveUserToFireStore(user)
            Result.Success(true)
        } catch (e: Exception) {
            Result.Error(e)
        }
    suspend fun login(email: String, password: String): Result<Boolean> =
        try {
            auth.signInWithEmailAndPassword(email, password).await()
            Result.Success(true)
        } catch (e: Exception) {
            Result.Error(e)
        }

        private suspend fun saveUserToFireStore(user: User) {
            firestore.collection("user").document(user.email).set(user).await()
        }

        object Injection {
            private val instance: FirebaseFirestore by lazy {
                FirebaseFirestore.getInstance()
            }

            fun instance(): FirebaseFirestore {
                return instance
            }
        }
    }

