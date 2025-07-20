package com.example.evops.core.presentation.components.navbar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.evops.core.navigation.SubGraph

@Composable
fun EvopsNavigationBar(navController: NavHostController, modifier: Modifier = Modifier) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    if (!SubGraph.Auth.isSelected(currentDestination)) {
        NavigationBar(modifier = modifier.fillMaxWidth()) {
            NavItemData.navItems.forEach { navItem ->
                val isSelected = navItem.subGraph.isSelected(currentDestination)
                NavItem(
                    navItemData = navItem,
                    onClick = { onNavItemClick(navController = navController, navItem = navItem) },
                    isSelected = isSelected,
                    modifier = Modifier,
                )
            }
        }
    }
}

private fun SubGraph.isSelected(currentDestination: NavDestination?): Boolean {
    if (currentDestination == null) {
        return false
    }
    val currentDestinationSubGraph = currentDestination.parent?.route?.shortRoute()
    val navItemSubGraph = this.toString()
    return currentDestinationSubGraph == navItemSubGraph
}

private fun String.shortRoute(): String? {
    return this.split(".").lastOrNull()
}

private fun onNavItemClick(navController: NavHostController, navItem: NavItemData) {
    navController.navigate(navItem.subGraph) {
        popUpTo(navController.graph.findStartDestination().id) {
            saveState = true
            inclusive = false
        }
        launchSingleTop = true
        restoreState = true
    }
}
