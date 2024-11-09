package com.example.proyekakhirpemrogramanmobile.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

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

    fun login(email: String, password: String, context: Context, navController: NavController) {
        if (email.isEmpty() or password.isEmpty()) {
            Toast.makeText(context, "Email or password cannot be empty", Toast.LENGTH_SHORT).show()
            return
        }
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    user = auth.currentUser
                    navController.navigate("home_screen") {
                        popUpTo(0) {
                            inclusive = true
                        }
                    }
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

    fun register(email: String, password: String, context: Context, navController: NavController) {
        if (email.isEmpty() or password.isEmpty()) {
            Toast.makeText(context, "Email or password cannot be empty", Toast.LENGTH_SHORT).show()
            return
        }
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    user = auth.currentUser
                    navController.navigate("home_screen") {
                        popUpTo(0) {
                            inclusive = true
                        }
                    }
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

    fun logout(navController: NavController, context: Context) {
        auth.signOut()
        user = null
        navController.navigate("base_screen") {
            popUpTo(0) {
                inclusive = true
            }
        }
        Toast.makeText(context, "Logout successful", Toast.LENGTH_SHORT).show()
    }

}