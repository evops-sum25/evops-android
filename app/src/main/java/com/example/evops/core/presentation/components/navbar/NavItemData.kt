package com.example.evops.core.presentation.components.navbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddCircleOutline
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.evops.core.navigation.Destination
import com.example.evops.core.navigation.SubGraph

data class NavItemData(
    val subGraph: SubGraph,
    val icon: ImageVector,
    val label: String,
    val startDestination: Destination,
) {
    companion object {
        val navItems =
            listOf(
                NavItemData(
                    subGraph = SubGraph.Home,
                    icon = Icons.Outlined.Home,
                    label = "Home",
                    startDestination = Destination.EventList,
                ),
                NavItemData(
                    subGraph = SubGraph.CreateEvent,
                    icon = Icons.Outlined.AddCircleOutline,
                    label = "Add event",
                    startDestination = Destination.CreateEvent,
                ),
            )
    }
}
