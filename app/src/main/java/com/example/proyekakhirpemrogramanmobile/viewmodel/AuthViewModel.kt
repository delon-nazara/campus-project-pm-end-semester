package com.example.proyekakhirpemrogramanmobile.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow

class AuthViewModel : ViewModel() {

    private var auth: FirebaseAuth = Firebase.auth
    private val _userState = MutableStateFlow<FirebaseUser?>(null)

    fun login(email: String, password: String, context: Context) {
        if (email.isEmpty() or password.isEmpty()) {
            Toast.makeText(context, "Email or password cannot be empty", Toast.LENGTH_SHORT).show()
            return
        }
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _userState.value = auth.currentUser
                    Toast.makeText(context, "Login successful", Toast.LENGTH_SHORT).show()
                } else {
                     val message = when (task.exception) {
                        is FirebaseAuthInvalidCredentialsException -> {
                            "Invalid email or password"
                        }
                        else -> {
                            task.exception?.localizedMessage ?: "Authentication Error"
                        }
                    }
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                }
            }
    }

    fun register(email: String, password: String, context: Context) {
        if (email.isEmpty() or password.isEmpty()) {
            Toast.makeText(context, "Email or password cannot be empty", Toast.LENGTH_SHORT).show()
            return
        }
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _userState.value = auth.currentUser
                    Toast.makeText(context, "Register successful", Toast.LENGTH_SHORT).show()
                } else {
                    val message = when (task.exception) {
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
                            task.exception?.localizedMessage ?: "Authentication Error"
                        }
                    }
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                }
            }
    }

}