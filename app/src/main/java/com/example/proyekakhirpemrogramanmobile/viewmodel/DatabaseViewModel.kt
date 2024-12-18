package com.example.proyekakhirpemrogramanmobile.viewmodel

import androidx.lifecycle.ViewModel
import com.example.proyekakhirpemrogramanmobile.data.model.LectureModel
import com.example.proyekakhirpemrogramanmobile.data.model.UserModel
import com.example.proyekakhirpemrogramanmobile.util.formatDate
import com.example.proyekakhirpemrogramanmobile.util.formatDay
import com.example.proyekakhirpemrogramanmobile.util.formatName
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
    private val lectureReference = database.collection("lecture")

    private var _userState = MutableStateFlow<UserModel?>(null)
    val userState: StateFlow<UserModel?> = _userState.asStateFlow()

    private var _lectureState = MutableStateFlow<List<LectureModel>>(emptyList())
    val lectureState: StateFlow<List<LectureModel>> = _lectureState.asStateFlow()

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
            finishedTasksId = null,
            coursesId = null,
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
                _userState.value = newUser
                onSuccess()
            }
            .addOnFailureListener {
                showLoading(false)
                _userState.value = null
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
                _userState.value = document.toObject(UserModel::class.java)
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

    fun getLectureState() {
        _userState.value?.coursesId?.let {
            lectureReference
                .whereIn("courseId", it)
                .get()
                .addOnSuccessListener { snapshot ->
                    _lectureState.value = snapshot.toObjects(LectureModel::class.java)
                }
        }
    }

    fun logout() {
        _userState.value = null
        _lectureState.value = emptyList()
    }

}