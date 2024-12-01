package com.example.proyekakhirpemrogramanmobile.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.cloudinary.android.MediaManager
import com.example.proyekakhirpemrogramanmobile.data.model.UserModel
import com.example.proyekakhirpemrogramanmobile.util.formatName
import com.example.proyekakhirpemrogramanmobile.util.getCurrentMilliseconds
import com.example.proyekakhirpemrogramanmobile.util.getFirstChar
import com.example.proyekakhirpemrogramanmobile.util.getFirstWord
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import io.github.cdimascio.dotenv.dotenv
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DatabaseViewModel : ViewModel() {

    private lateinit var cloudinaryName: String
    private lateinit var cloudinaryPreset: String

    fun cloudinaryInitialization(context: Context) {
        val dotenv = dotenv {
            directory = "/assets"
            filename = "env"
        }

        cloudinaryName = dotenv["CLOUD_NAME"]
        cloudinaryPreset = dotenv["UPLOAD_PRESET_UNSIGNED"]

        val config = hashMapOf(
            "cloud_name" to cloudinaryName,
            "secure" to true
        )
        MediaManager.init(context, config)
    }

    private val database: FirebaseFirestore = Firebase.firestore
    private val userReference = database.collection("user")

    private var _userDataState = MutableStateFlow<UserModel?>(null)
    val userDataState: StateFlow<UserModel?> = _userDataState.asStateFlow()

    fun addUserToDatabase(
        userId: String,
        email: String,
        fullName: String,
        studentId: String,
        gender: String,
        showLoading: (Boolean) -> Unit,
        onSuccess: () -> Unit,
        onFailure: () -> Unit
    ) {
        val cleanName = formatName(fullName)
        val firstChar = getFirstChar(cleanName)
        val profileUrl = "https://res.cloudinary.com/${cloudinaryName}/image/upload/alphabet_profile_picture_${firstChar}"
        val currentTime = getCurrentMilliseconds().toString()

        val newUser = UserModel(
            email = email,
            gender = gender,
            fullName = cleanName,
            userName = getFirstWord(cleanName),
            createdAt = currentTime,
            studentId = studentId,
            profileUrl = profileUrl
        )

        showLoading(true)
        userReference.document(userId)
            .set(newUser)
            .addOnSuccessListener {
                showLoading(false)
                updateUserState(newUser)
                onSuccess()
            }
            .addOnFailureListener {
                showLoading(false)
                updateUserState(null)
                onFailure()
            }
    }

    fun getUserFromDatabase(
        userId: String,
        showLoading: (Boolean) -> Unit,
        onSuccess: () -> Unit,
        onFailure: () -> Unit
    ) {
        showLoading(true)
        userReference.document(userId)
            .get()
            .addOnSuccessListener { document ->
                showLoading(false)
                val data = document.data!!
                val userData = UserModel(
                    email = data["email"].toString(),
                    gender = data["gender"].toString(),
                    fullName = data["fullName"].toString(),
                    userName = data["userName"].toString(),
                    createdAt = data["createdAt"].toString(),
                    studentId = data["studentId"].toString(),
                    profileUrl = data["profileUrl"].toString()
                )
                updateUserState(userData)
                onSuccess()
            }
            .addOnFailureListener {
                showLoading(false)
                onFailure()
            }
    }

    fun checkUserFromDatabase(
        userId: String,
        showLoading: (Boolean) -> Unit,
        onUserExist: () -> Unit,
        onUserNotExist: () -> Unit,
        onFailure: () -> Unit
    ) {
        showLoading(true)
        userReference.document(userId)
            .get()
            .addOnSuccessListener { document ->
                showLoading(false)
                if (document.exists()) {
                    onUserExist()
                } else {
                    onUserNotExist()
                }
            }
            .addOnFailureListener {
                showLoading(false)
                onFailure()
            }
    }

    private fun updateUserState(state: UserModel?) {
        _userDataState.value = state
    }

}