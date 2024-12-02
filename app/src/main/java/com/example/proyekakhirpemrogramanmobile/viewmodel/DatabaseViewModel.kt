package com.example.proyekakhirpemrogramanmobile.viewmodel

import androidx.lifecycle.ViewModel
import com.example.proyekakhirpemrogramanmobile.data.model.UserModel
import com.example.proyekakhirpemrogramanmobile.util.formatName
import com.example.proyekakhirpemrogramanmobile.util.getCurrentMilliseconds
import com.example.proyekakhirpemrogramanmobile.util.getFirstLetter
import com.example.proyekakhirpemrogramanmobile.util.getFirstWord
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DatabaseViewModel : ViewModel() {

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
        val firstWord = getFirstWord(cleanName)
        val firstLetter = getFirstLetter(cleanName).toString()
        val currentTime = getCurrentMilliseconds().toString()

        val newUser = UserModel(
            email = email,
            gender = gender,
            firstWord = firstWord,
            fullName = cleanName,
            studentId = studentId,
            createdAt = currentTime,
            firstLetter = firstLetter
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
                    firstWord = data["firstWord"].toString(),
                    fullName = data["fullName"].toString(),
                    studentId = data["studentId"].toString(),
                    createdAt = data["createdAt"].toString(),
                    firstLetter = data["firstLetter"].toString()
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