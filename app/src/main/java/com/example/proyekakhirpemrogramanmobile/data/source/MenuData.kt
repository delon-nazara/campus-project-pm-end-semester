package com.example.proyekakhirpemrogramanmobile.data.source

import com.example.proyekakhirpemrogramanmobile.R

enum class Menu(
    val title: Int,
    val icon: Int,
    val destination: String
) {
    HOME(
        title = R.string.sb_home,
        icon = R.drawable.home_icon,
        destination = Route.HOME_SCREEN.name
    ),
    SCHEDULE(
        title = R.string.sb_schedule,
        icon = R.drawable.schedule_icon,
        destination = Route.SCHEDULE_SCREEN.name
    ),
    COURSE(
        title = R.string.sb_course,
        icon = R.drawable.course_icon,
        destination = Route.COURSE_SCREEN.name
    ),
    TASK(
        title = R.string.sb_task,
        icon = R.drawable.task_icon,
        destination = Route.TASK_SCREEN.name
    ),
    MODULE(
        title = R.string.sb_module,
        icon = R.drawable.module_icon,
        destination = Route.MODULE_SCREEN.name
    ),
    ANNOUNCEMENT(
        title = R.string.sb_announcement,
        icon = R.drawable.announcement_icon,
        destination = Route.ANNOUNCEMENT_SCREEN.name
    ),
    TOOL(
        title = R.string.sb_tool,
        icon = R.drawable.tool_icon,
        destination = Route.TOOL_SCREEN.name
    ),
//    ADMIN(
//        title = R.string.sb_admin,
//        icon = R.drawable.admin_icon,
//        destination = Route.ADMIN_SCREEN.name
//    ),
    SETTING(
        title = R.string.sb_setting,
        icon = R.drawable.setting_icon,
        destination = Route.SETTING_SCREEN.name
    ),
}