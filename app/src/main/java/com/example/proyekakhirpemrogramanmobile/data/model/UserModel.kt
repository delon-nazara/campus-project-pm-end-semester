package com.example.proyekakhirpemrogramanmobile.data.model

import com.google.firebase.firestore.FieldValue

data class UserModel(
    val fullName: String,
    val firstLetter: String,
    val studentId: String,
    val email: String,
    val createdAt: FieldValue
)
