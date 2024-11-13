package com.example.proyekakhirpemrogramanmobile.viewmodel

import androidx.lifecycle.ViewModel
import com.example.proyekakhirpemrogramanmobile.utils.showToast
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class DatabaseViewModel : ViewModel() {

    private val database: FirebaseFirestore = Firebase.firestore
    private val usersRef = database.collection("users")

    suspend fun saveUserToDatabase(user: FirebaseUser): String {
        return try {
            usersRef
                .document(user.uid)
                .set(
                    hashMapOf(
                        "email" to user.email,
                        "createdAt" to FieldValue.serverTimestamp()
                    )
                )
                .await()
            "Successful"
        } catch(e: FirebaseFirestoreException) {
            e.localizedMessage ?: "Save User Error"
        }
    }

    fun userExistInDatabase(userId: String): Boolean {
        var result = false
        usersRef
            .document(userId)
            .get()
            .addOnSuccessListener {
                result = true
            }
        return result
    }

}