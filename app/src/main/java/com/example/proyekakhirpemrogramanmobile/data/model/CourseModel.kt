package com.example.proyekakhirpemrogramanmobile.data.model

data class CourseModel(
    val courseId: String = "",
    val courseName: String = "",
    val credits: String = "",
    val major: String = "",
    val faculty: String = "",
    val leader: String = "",
    val lecturer: String = "",
    val semester: String = "",
    val year: String = "",
    val amount: Map<String, String> = emptyMap(),
    val created: Map<String, String> = emptyMap(),
    val location: Map<String, String> = emptyMap(),
    val schedule: Map<String, String> = emptyMap(),
)
