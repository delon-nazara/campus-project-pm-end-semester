package com.example.proyekakhirpemrogramanmobile.data.model

enum class LectureStatus {
    PRESENT,    // perkuliahan diadakan
    UNKNOWN,    // tidak ada informasi
    CANCELLED   // perkuliahan dibatalkan
}

data class LectureModel(
    val courseId: String = "",
    val courseName: String = "",
    val number: String = "",
    val notes: String = "",
    val summary: String = "",
    val status: String = "",
    val schedule: Map<String, String> = emptyMap(),
    val location: Map<String, String> = emptyMap(),
)