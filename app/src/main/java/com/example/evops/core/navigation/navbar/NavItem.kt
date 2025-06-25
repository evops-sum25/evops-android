package com.example.evops.core.navigation.navbar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun RowScope.NavItem(
    navItemData: NavItemData,
    onClick: () -> Unit,
    isSelected: Boolean,
    modifier: Modifier = Modifier
) {
    NavigationBarItem(
        selected = isSelected,
        onClick = onClick,
        icon = { Icon(navItemData.icon, contentDescription = navItemData.label) },
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = MaterialTheme.colorScheme.primary,
            unselectedIconColor = Color.Red
        ),
        modifier = modifier,
    )
}