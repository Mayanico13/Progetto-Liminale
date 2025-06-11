package com.example.progettoparabellum.ui.screen.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import com.example.progettoparabellum.Routes

object BottomBarIcons {
    val BottomNavItems = listOf(
        BottomNavItem(
            label = "Home",
            icon = Icons.Filled.Home,
            route = Routes.Home.route
        ),
        BottomNavItem(
            label = "Settings",
            icon = Icons.Filled.Settings,
            route = "settings"
        ),
        BottomNavItem(
            label = "New post",
            icon = Icons.Filled.Add,
            route = Routes.CreatePost.route
        )
    )
}