package com.example.proyekakhirpemrogramanmobile.data.source

import com.example.proyekakhirpemrogramanmobile.R

enum class Menu(
    val title: Int,
    val icon: Int
) {
    HOME(
        title = R.string.sidebar_home,
        icon = R.drawable.home_icon
    ),
    SCHEDULE(
        title = R.string.sidebar_schedule,
        icon = R.drawable.schedule_icon
    ),
    COURSE(
        title = R.string.sidebar_course,
        icon = R.drawable.course_icon
    ),
    TASK(
        title = R.string.sidebar_task,
        icon = R.drawable.task_icon
    ),
    MODULE(
        title = R.string.sidebar_module,
        icon = R.drawable.module_icon
    ),
    ANNOUNCEMENT(
        title = R.string.sidebar_announcement,
        icon = R.drawable.announcement_icon
    ),
    TOOL(
        title = R.string.sidebar_tool,
        icon = R.drawable.tool_icon
    ),
    SETTING(
        title = R.string.sidebar_setting,
        icon = R.drawable.setting_icon
    ),
}