package com.example.proyekakhirpemrogramanmobile.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.cloudinary.android.MediaManager
import com.example.proyekakhirpemrogramanmobile.data.model.UserModel
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import io.github.cdimascio.dotenv.dotenv
import kotlinx.coroutines.tasks.await

class DatabaseViewModel : ViewModel() {

    private val database: FirebaseFirestore = Firebase.firestore
    private val usersRef = database.collection("users")
    private val classesRef = database.collection("classes")

    suspend fun addUserToDatabase(user: FirebaseUser, fullName: String, studentId: String): String {
        return try {
            usersRef
                .document(user.uid)
                .set(
                    UserModel(
                        fullName = fullName,
                        firstLetter = fullName[0].toString(),
                        studentId = studentId,
                        email = user.email!!,
                        createdAt = FieldValue.serverTimestamp()
                    )
                )
                .await()
            "Successful"
        } catch(e: FirebaseFirestoreException) {
            e.localizedMessage ?: "Add User Error"
        }
    }

    suspend fun userExistInDatabase(userId: String): Boolean {
        return try {
            val userDocument = usersRef
                .document(userId)
                .get()
                .await()
            userDocument.exists()
        } catch(e: FirebaseFirestoreException) {
            false
        }
    }

    // todo
    fun cloudinaryInitialization(context: Context) {
        val dotenv = dotenv {
            directory = "/assets"
            filename = "env"
        }

        val config = hashMapOf(
            "cloud_name" to dotenv["CLOUD_NAME"],
            "secure" to true
        )
        MediaManager.init(context, config)
    }

    // todo
    fun getImageUrlFromCloudinary(): String {
        return MediaManager.get().url().generate("alphabet_profile_picture_d")
    }

}