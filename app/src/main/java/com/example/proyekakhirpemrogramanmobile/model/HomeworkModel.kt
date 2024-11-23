package com.example.proyekakhirpemrogramanmobile.model

enum class HomeworkType {
    PERSONAL,
    GROUP
}

data class HomeworkModel(
    val subject: String,
    val deadline: String, // todo
    val title: String,
    val type: HomeworkType
)
