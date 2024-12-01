package com.example.proyekakhirpemrogramanmobile.viewmodel

import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import com.example.proyekakhirpemrogramanmobile.R
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.tasks.await

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
                    _userAuthState.value = result.user
                    onSuccess()
                    clearErrorState()
                }
                .addOnFailureListener { exception ->
                    showLoading(false)
                    _userAuthState.value = null
                    when (exception) {
                        is FirebaseAuthUserCollisionException -> {
                            _errorEmailState.value = "Email has been used"
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
        onSuccess: () -> Unit,
        onFailure: () -> Unit
    ) {
        if (isEmailInputValid(email) && isPasswordInputValid(password)) {
            showLoading(true)
            authentication.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener { result ->
                    showLoading(false)
                    _userAuthState.value = result.user
                    clearErrorState()
                    onSuccess()
                }
                .addOnFailureListener { exception ->
                    showLoading(false)
                    _userAuthState.value = null
                    when (exception) {
                        is FirebaseAuthInvalidCredentialsException -> {
                            _errorAllState.value = "Email atau password salah"
                        }
                        else -> {
                            onFailure()
                        }
                    }
                }
        }
    }

    fun logout() {
        authentication.signOut()
        _userAuthState.value = null
        showLoading(false)
        clearErrorState()
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
            _errorPasswordState.value = "Password tidak boleh kosong"
            return false
        } else if (password.length < 6) {
            _errorPasswordState.value = "Password harus terdiri dari minimal 6 karakter"
            return false
        } else {
            _errorPasswordState.value = null
            return true
        }
    }

    private fun isNameInputValid(name: String): Boolean {
        if (name.isEmpty()) {
            _errorFullNameState.value = "Name cannot be empty"
            return false
        } else if (!name.matches(Regex("^[a-zA-Z ]+$"))) {
            _errorFullNameState.value = "Name can only consist of alphabet"
            return false
        } else if (name.length < 3 || name.length > 30) {
            _errorFullNameState.value = "Name must consist of 3-30 characters"
            return false
        } else {
            _errorFullNameState.value = null
            return true
        }
    }

    private fun clearErrorState() {
        _errorEmailState.value = null
        _errorPasswordState.value = null
        _errorFullNameState.value = null
        _errorAllState.value = null
    }

}