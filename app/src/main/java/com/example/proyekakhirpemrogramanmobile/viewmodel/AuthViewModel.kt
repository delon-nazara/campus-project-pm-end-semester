package com.example.proyekakhirpemrogramanmobile.viewmodel

import androidx.lifecycle.ViewModel
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class AuthViewModel : ViewModel() {

    private var auth: FirebaseAuth = Firebase.auth
    var user: FirebaseUser? = auth.currentUser

    fun startDestinationBasedAuth(): String {
        return if (user == null) {
            "base_screen"
        } else {
            "home_screen"
        }
    }

    suspend fun register(email: String, password: String): String {
        return try {
            auth.createUserWithEmailAndPassword(email, password).await()
            user = auth.currentUser
            "Successful"
        } catch (e: FirebaseException) {
            return when (e) {
                is FirebaseAuthUserCollisionException -> {
                    "Email has been used"
                }
                is FirebaseAuthWeakPasswordException -> {
                    "Password must consist of 6-15 characters"
                }
                is FirebaseAuthInvalidCredentialsException -> {
                    "Invalid email or password"
                }
                else -> {
                    e.localizedMessage ?: "Error Authentication"
                }
            }
        }
    }

    suspend fun login(email: String, password: String): String {
        return try {
            auth.signInWithEmailAndPassword(email, password).await()
            user = auth.currentUser
            "Successful"
        } catch (e: FirebaseException) {
            return when (e) {
                is FirebaseAuthInvalidCredentialsException -> {
                    "Invalid email or password"
                }
                else -> {
                    e.localizedMessage ?: "Error Authentication"
                }
            }
        }
    }

    fun logout() {
        auth.signOut()
        user = null
    }

}