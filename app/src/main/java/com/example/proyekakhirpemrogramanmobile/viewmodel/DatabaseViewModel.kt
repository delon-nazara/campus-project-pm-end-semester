package com.example.proyekakhirpemrogramanmobile.viewmodel

import androidx.lifecycle.ViewModel
import com.example.proyekakhirpemrogramanmobile.data.model.AnnouncementModel
import com.example.proyekakhirpemrogramanmobile.data.model.LectureModel
import com.example.proyekakhirpemrogramanmobile.data.model.TaskModel
import com.example.proyekakhirpemrogramanmobile.data.model.UserModel
import com.example.proyekakhirpemrogramanmobile.data.model.CourseModel
import com.example.proyekakhirpemrogramanmobile.data.model.ModuleModel
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
    private val courseReference = database.collection("course")
    private val taskReference = database.collection("task")
    private val moduleReference = database.collection("module")
    private val announcementReference = database.collection("announcement")

    private var _userState = MutableStateFlow<UserModel?>(null)
    val userState: StateFlow<UserModel?> = _userState.asStateFlow()

    private var _lectureState = MutableStateFlow<List<LectureModel>>(emptyList())
    val lectureState: StateFlow<List<LectureModel>> = _lectureState.asStateFlow()

    private var _courseState = MutableStateFlow<List<CourseModel>>(emptyList())
    val courseState: StateFlow<List<CourseModel>> = _courseState.asStateFlow()

    private var _taskState = MutableStateFlow<List<TaskModel>>(emptyList())
    val taskState: StateFlow<List<TaskModel>> = _taskState.asStateFlow()

    private var _moduleState = MutableStateFlow<List<ModuleModel>>(emptyList())
    val moduleState: StateFlow<List<ModuleModel>> = _moduleState.asStateFlow()

    private var _announcementState = MutableStateFlow<List<AnnouncementModel>>(emptyList())
    val announcementState: StateFlow<List<AnnouncementModel>> = _announcementState.asStateFlow()

    private var _selectedCourseIdState = MutableStateFlow("")
    val selectedCourseIdState: StateFlow<String> = _selectedCourseIdState.asStateFlow()

    private var _selectedTaskIdState = MutableStateFlow("")
    val selectedTaskIdState: StateFlow<String> = _selectedTaskIdState.asStateFlow()

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
            coursesId = emptyList(),
            created = mapOf(
                "day" to day,
                "date" to date,
                "time" to time,
            ),
        )

        showLoading(true)
        userReference
            .document(userId)
            .set(newUser)
            .addOnSuccessListener {
                showLoading(false)
                _userState.value = newUser
                getAllData()
                onSuccess()
            }
            .addOnFailureListener {
                showLoading(false)
                _userState.value = null
                deleteAllData()
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
        userReference
            .document(userId)
            .get()
            .addOnSuccessListener { document ->
                showLoading(false)
                _userState.value = document.toObject(UserModel::class.java)
                getAllData()
                onSuccess()
            }
            .addOnFailureListener {
                showLoading(false)
                _userState.value = null
                deleteAllData()
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
        userReference
            .document(userId)
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

    private fun getLectureData() {
        _userState.value?.coursesId?.let {
            lectureReference
                .whereIn("courseId", it)
                .addSnapshotListener { snapshot, e ->
                    if (e == null) {
                        _lectureState.value = snapshot?.toObjects(LectureModel::class.java) ?: emptyList()
                    } else {
                        _lectureState.value = emptyList()
                    }
                }
        }
    }

    private fun getCourseData() {
        _userState.value?.coursesId?.let {
            courseReference
                .whereIn("courseId", it)
                .addSnapshotListener { snapshot, e ->
                    if (e == null) {
                        _courseState.value = snapshot?.toObjects(CourseModel::class.java) ?: emptyList()
                    } else {
                        _courseState.value = emptyList()
                    }
                }
        }
    }

    private fun getTaskData() {
        _userState.value?.coursesId?.let {
            taskReference
                .whereIn("courseId", it)
                .addSnapshotListener { snapshot, e ->
                    if (e == null) {
                        _taskState.value = snapshot?.toObjects(TaskModel::class.java) ?: emptyList()
                    } else {
                        _taskState.value = emptyList()
                    }
                }
        }
    }

    private fun getModuleData() {
        _userState.value?.coursesId?.let {
            moduleReference
                .whereIn("courseId", it)
                .addSnapshotListener { snapshot, e ->
                    if (e == null) {
                        _moduleState.value = snapshot?.toObjects(ModuleModel::class.java) ?: emptyList()
                    } else {
                        _moduleState.value = emptyList()
                    }
                }
        }
    }

    private fun getAnnouncementData() {
        _userState.value?.coursesId?.let {
            announcementReference
                .whereIn("courseId", it)
                .addSnapshotListener { snapshot, e ->
                    if (e == null) {
                        _announcementState.value = snapshot?.toObjects(AnnouncementModel::class.java) ?: emptyList()
                    } else {
                        _announcementState.value = emptyList()
                    }
                }
        }
    }

    fun setSelectedCourseIdState(courseId: String) {
        _selectedCourseIdState.value = courseId
    }

    fun setSelectedTaskIdState(taskId: String) {
        _selectedTaskIdState.value = taskId
    }

    private fun getAllData() {
        getLectureData()
        getCourseData()
        getTaskData()
        getModuleData()
        getAnnouncementData()
    }

    private fun deleteAllData() {
        _lectureState.value = emptyList()
        _courseState.value = emptyList()
        _taskState.value = emptyList()
        _moduleState.value = emptyList()
        _announcementState.value = emptyList()
        _selectedCourseIdState.value = ""
        _selectedTaskIdState.value = ""
    }

    fun logout() {
        _userState.value = null
        deleteAllData()
    }

}