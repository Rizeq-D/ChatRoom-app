package com.example.chatroomapp.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class UserRepository(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) {

    suspend fun signUp(email: String, password: String, firstName: String, lastName: String): Result<Boolean> {
        return try {
            val authResult = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            val firebaseUser = authResult.user
            if (firebaseUser != null) {
                val user = User(
                    email = email,
                    firstName = firstName,
                    lastName = lastName
                )
                firestore.collection("users").document(firebaseUser.uid).set(user).await()
                Result.Success(true)
            } else {
                Result.Error(Exception("Sign up failed"))
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    suspend fun login(email: String, password: String): Result<Boolean> {
        return try {
            firebaseAuth.signInWithEmailAndPassword(email, password).await()
            Result.Success(true)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    suspend fun getCurrentUser(): Result<User?> {
        val firebaseUser: FirebaseUser? = firebaseAuth.currentUser
        return if (firebaseUser != null) {
            val documentSnapshot = firestore.collection("users").document(firebaseUser.uid).get().await()
            val user = documentSnapshot.toObject(User::class.java)
            Result.Success(user)
        } else {
            Result.Error(Exception("No current user"))
        }
    }

    object Injection {
        fun instance(): FirebaseFirestore {
            return FirebaseFirestore.getInstance()
        }
    }
}

