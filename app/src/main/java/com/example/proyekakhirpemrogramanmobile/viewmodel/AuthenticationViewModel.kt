package com.example.proyekakhirpemrogramanmobile.viewmodel

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AuthenticationViewModel : ViewModel() {

    private var authentication: FirebaseAuth = Firebase.auth

    private var _userAuthState = MutableStateFlow(authentication.currentUser)
    val userAuthState: StateFlow<FirebaseUser?> = _userAuthState.asStateFlow()

    private var _errorEmailState = MutableStateFlow<String?>(null)
    val errorEmailState: StateFlow<String?> = _errorEmailState.asStateFlow()

    private var _errorPasswordState = MutableStateFlow<String?>(null)
    val errorPasswordState: StateFlow<String?> = _errorPasswordState.asStateFlow()

    private var _errorFullNameState = MutableStateFlow<String?>(null)
    val errorFullNameState: StateFlow<String?> = _errorFullNameState.asStateFlow()

    private var _errorStudentIdState = MutableStateFlow<String?>(null)
    val errorStudentIdState: StateFlow<String?> = _errorStudentIdState.asStateFlow()

    private var _errorGenderState = MutableStateFlow<String?>(null)
    val errorGenderState: StateFlow<String?> = _errorGenderState.asStateFlow()

    private var _errorAllState = MutableStateFlow<String?>(null)
    val errorAllState: StateFlow<String?> = _errorAllState.asStateFlow()

    fun register(
        email: String,
        password: String,
        showLoading: (Boolean) -> Unit,
        onSuccess: () -> Unit,
        onFailure: () -> Unit
    ) {
        if (isEmailInputValid(email) && isPasswordInputValid(password)) {
            showLoading(true)
            authentication.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener { result ->
                    showLoading(false)
                    updateUserState(result.user)
                    clearErrorState()
                    onSuccess()
                }
                .addOnFailureListener { exception ->
                    showLoading(false)
                    updateUserState(null)
                    when (exception) {
                        is FirebaseAuthUserCollisionException -> {
                            _errorEmailState.value = "Email telah terdaftar"
                        }
                        else -> {
                            onFailure()
                        }
                    }
                }
        }
    }

    fun login(
        email: String,
        password: String,
        showLoading: (Boolean) -> Unit,
        onSuccess: (String) -> Unit,
        onFailure: () -> Unit
    ) {
        if (isEmailInputValid(email) && isPasswordInputValid(password)) {
            showLoading(true)
            authentication.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener { result ->
                    showLoading(false)
                    updateUserState(result.user)
                    clearErrorState()
                    onSuccess(result.user!!.uid)
                }
                .addOnFailureListener { exception ->
                    showLoading(false)
                    updateUserState(null)
                    when (exception) {
                        is FirebaseAuthInvalidCredentialsException -> {
                            _errorAllState.value = "Email atau kata sandi salah"
                        }
                        else -> {
                            onFailure()
                        }
                    }
                }
        }
    }

    private fun isEmailInputValid(email: String): Boolean {
        if (email.isEmpty()) {
            _errorEmailState.value = "Email tidak boleh kosong"
            return false
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _errorEmailState.value = "Alamat email tidak valid"
            return false
        } else {
            _errorEmailState.value = null
            return true
        }
    }

    private fun isPasswordInputValid(password: String): Boolean {
        if (password.isEmpty()) {
            _errorPasswordState.value = "Kata sandi tidak boleh kosong"
            return false
        } else if (password.length < 6) {
            _errorPasswordState.value = "Kata sandi harus terdiri dari minimal 6 karakter"
            return false
        } else {
            _errorPasswordState.value = null
            return true
        }
    }

    fun isFullNameInputValid(fullName: String): Boolean {
        if (fullName.isEmpty()) {
            _errorFullNameState.value = "Nama tidak boleh kosong"
            return false
        } else if (!fullName.matches(Regex("^[a-zA-Z ]+$"))) {
            _errorFullNameState.value = "Nama hanya boleh terdiri dari huruf"
            return false
        } else if (fullName.length < 3 || fullName.length > 30) {
            _errorFullNameState.value = "Nama harus terdiri dari 3-30 karakter"
            return false
        } else {
            _errorFullNameState.value = null
            return true
        }
    }

    fun isStudentIdValid(studentId: String): Boolean {
        if (studentId.isEmpty()) {
            _errorStudentIdState.value = "NIM tidak boleh kosong"
            return false
        } else if (!studentId.matches(Regex("^[0-9]+$"))) {
            _errorStudentIdState.value = "NIM hanya boleh terdiri dari angka"
            return false
        } else if (studentId.length != 9) {
            _errorStudentIdState.value = "NIM harus terdiri dari tepat 9 angka"
            return false
        } else {
            _errorStudentIdState.value = null
            return true
        }
    }

    fun isGenderValid(gender: String): Boolean {
        if (gender == "Jenis Kelamin") {
            _errorGenderState.value = "Pilih salah satu dari opsi gender yang ada"
            return false
        } else {
            _errorGenderState.value = null
            return true
        }
    }

    fun logout() {
        _userAuthState.value = null
        authentication.signOut()
    }

    fun clearErrorState() {
        _errorEmailState.value = null
        _errorPasswordState.value = null
        _errorFullNameState.value = null
        _errorStudentIdState.value = null
        _errorGenderState.value = null
        _errorAllState.value = null
    }

    private fun updateUserState(state: FirebaseUser?) {
        _userAuthState.value = state
    }

}