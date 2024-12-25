package com.example.proyekakhirpemrogramanmobile.data.source

import com.example.proyekakhirpemrogramanmobile.R
import com.example.proyekakhirpemrogramanmobile.data.model.ToolModel

val listTool = listOf(
    ToolModel(
        image = R.drawable.spin_wheel_icon,
        name = R.string.tls_spin_wheel,
        route = Route.TOOL_SPIN_WHEEL_SCREEN.name
    ),
    ToolModel(
        image = R.drawable.voting_icon,
        name = R.string.tls_voting,
        route = Route.TOOL_VOTING_LIST_SCREEN.name
    )
)