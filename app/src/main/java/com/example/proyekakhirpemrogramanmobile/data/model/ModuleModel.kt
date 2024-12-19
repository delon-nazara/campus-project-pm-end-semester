package com.example.proyekakhirpemrogramanmobile.data.model

data class ModuleModel(
    val courseId: String = "",
    val courseName: String = "",
    val type: String = "",
    val title: String = "",
    val link: String = "",
    val created: Map<String, String> = emptyMap(),
)
