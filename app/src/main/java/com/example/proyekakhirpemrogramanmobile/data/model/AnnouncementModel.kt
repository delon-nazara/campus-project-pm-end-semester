package com.example.proyekakhirpemrogramanmobile.data.model

data class AnnouncementModel(
    val courseId: String = "",
    val courseName: String = "",
    val title: String = "",
    val description: String = "",
    val created: Map<String, String> = emptyMap(),
)
