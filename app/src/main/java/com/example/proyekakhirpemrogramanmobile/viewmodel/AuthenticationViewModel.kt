package com.example.proyekakhirpemrogramanmobile.viewmodel

import androidx.lifecycle.ViewModel
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class AuthenticationViewModel : ViewModel() {

    private var authentication: FirebaseAuth = Firebase.auth
    var currentUser: FirebaseUser? = authentication.currentUser

    fun startDestinationBasedAuth(): String {
        return if (currentUser == null) {
            "base_screen"
        } else {
            "home_screen"
        }
    }

    suspend fun register(email: String, password: String): String {
        return try {
            authentication.createUserWithEmailAndPassword(email, password).await()
            currentUser = authentication.currentUser
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
                    e.localizedMessage ?: "Authentication Error"
                }
            }
        }
    }

    suspend fun login(email: String, password: String): String {
        return try {
            authentication.signInWithEmailAndPassword(email, password).await()
            currentUser = authentication.currentUser
            "Successful"
        } catch(e: FirebaseAuthException) {
            return when (e) {
                is FirebaseAuthInvalidCredentialsException -> {
                    "Invalid email or password"
                }
                else -> {
                    e.localizedMessage ?: "Authentication Error"
                }
            }
        }
    }

    fun logout() {
        authentication.signOut()
        currentUser = null
    }

}