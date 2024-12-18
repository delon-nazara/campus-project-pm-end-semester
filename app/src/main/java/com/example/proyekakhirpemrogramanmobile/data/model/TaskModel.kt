package com.example.proyekakhirpemrogramanmobile.data.model

enum class TaskType {
    PERSONAL,   // tugas pribadi
    GROUP       // tugas kelompok
}

data class TaskModel(
    val courseId: String = "",
    val course: String = "",
    val title: String = "",
    val type: String = "",
    val description: String = "",
    val submissionLink: String = "",
    val created: Map<String, String> = emptyMap(),
    val assigned: Map<String, String> = emptyMap(),
    val deadline: Map<String, String> = emptyMap(),
)
