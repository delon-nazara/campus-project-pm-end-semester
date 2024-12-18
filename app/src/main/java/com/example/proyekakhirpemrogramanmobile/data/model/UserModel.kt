package com.example.proyekakhirpemrogramanmobile.data.model

data class UserModel(
    val email: String = "",
    val gender: String = "",
    val fullName: String = "",
    val firstWord: String = "",
    val studentId: String = "",
    val firstLetter: String = "",
    val courseId: List<String> = emptyList(),
    val created: Map<String, String> = emptyMap(),
    val finishedTaskId: List<String> = emptyList(),
)