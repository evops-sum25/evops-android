package com.example.evops.core.navigation.navbar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun EvOpsNavigationBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar(modifier = modifier.fillMaxWidth()) {
        NavItemData.navItems.forEach { item ->
            val isSelected = currentDestination?.hierarchy == item.subGraph
            NavItem(
                navItemData = item,
                onClick = { navController.navigate(item.subGraph) },
                isSelected = isSelected,
                modifier = Modifier
            )
        }
    }
}