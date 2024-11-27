package com.example.proyekakhirpemrogramanmobile.model

enum class TaskType {
    PERSONAL,
    GROUP
}

data class TaskModel(
    val subject: String,
    val deadline: String, // todo
    val title: String,
    val type: TaskType
)
