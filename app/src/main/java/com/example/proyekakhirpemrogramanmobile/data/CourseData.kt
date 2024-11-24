package com.example.proyekakhirpemrogramanmobile.data

import com.example.proyekakhirpemrogramanmobile.R
import com.example.proyekakhirpemrogramanmobile.model.CourseModel

val listCourse = listOf(
    CourseModel(
        course = "Pemrograman Mobile",
        lecture = "Nurrahmadayeni M.Kom",
        students = 36,
        semester = 5,
        image = R.drawable.course_image_1
    ),
    CourseModel(
        course = "Grafika Komputer",
        lecture = "Herriyance S.T., M.Kom.",
        students = 30,
        semester = 5,
        image = R.drawable.course_image_2
    ),
//    CourseModel(
//        course = "Komputasi Paralel dan Terdistribusi",
//        lecture = "Handrizal S.Si., M.Comp.Sc",
//        students = 28,
//        semester = 7,
//        image = R.drawable.course_image_3
//    ),
    CourseModel(
        course = "Business Intelligence",
        lecture = "Sri Melvani Hardi S.Kom., M.Kom",
        students = 53,
        semester = 7,
        image = R.drawable.course_image_4
    ),
)

//val listCourse = emptyList<CourseModel>()