package com.example.proyekakhirpemrogramanmobile.viewmodel

import androidx.compose.animation.core.snap
import androidx.lifecycle.ViewModel
import com.example.proyekakhirpemrogramanmobile.data.model.UserModel
import com.example.proyekakhirpemrogramanmobile.util.formatDate
import com.example.proyekakhirpemrogramanmobile.util.formatDay
import com.example.proyekakhirpemrogramanmobile.util.formatName
import com.example.proyekakhirpemrogramanmobile.util.formatDisplayTime
import com.example.proyekakhirpemrogramanmobile.util.formatTime
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
        val day = formatDay(getCurrentMilliseconds())
        val date = formatDate(getCurrentMilliseconds())
        val time = formatTime(getCurrentMilliseconds())

        val newUser = UserModel(
            email = email,
            gender = gender,
            fullName = cleanName,
            studentId = studentId,
            firstWord = firstWord,
            firstLetter = firstLetter,
            finishedTaskId = emptyList(),
            courseId = emptyList(),
            created = mapOf(
                "day" to day,
                "date" to date,
                "time" to time,
            ),
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
                val userData = document.toObject(UserModel::class.java)
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
            .addOnFailureListener { e ->
                showLoading(false)
                onFailure()
            }
    }

    private fun updateUserState(state: UserModel?) {
        _userDataState.value = state
    }

    fun logout() {
        _userDataState.value = null
    }

}