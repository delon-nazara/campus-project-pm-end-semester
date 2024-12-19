package com.example.proyekakhirpemrogramanmobile.data.model

data class UserModel(
    val userId: String = "",
    val email: String = "",
    val gender: String = "",
    val fullName: String = "",
    val firstWord: String = "",
    val studentId: String = "",
    val firstLetter: String = "",
    val created: Map<String, String> = emptyMap(),
    val coursesId: List<String> = emptyList(),
)