package com.example.proyekakhirpemrogramanmobile.data.model.archive

enum class TaskType {
    PERSONAL,
    GROUP
}

data class TaskModel(
    val course: String,
    val deadline: String, // todo
    val title: String,
    val type: TaskType
)
