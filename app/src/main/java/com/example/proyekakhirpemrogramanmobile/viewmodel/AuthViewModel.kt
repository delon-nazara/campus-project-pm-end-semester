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
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _userState.value = auth.currentUser
                    Toast.makeText(context, "Login successful", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, task.exception?.localizedMessage, Toast.LENGTH_SHORT).show()
                }
            }
    }

    fun register(email: String, password: String, context: Context) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _userState.value = auth.currentUser
                    Toast.makeText(context, "Register successful", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, task.exception?.localizedMessage, Toast.LENGTH_SHORT).show()
                }
            }
    }

}