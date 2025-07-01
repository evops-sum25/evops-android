package com.example.evops.core.navigation.navbar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun RowScope.NavItem(
    navItemData: NavItemData,
    onClick: () -> Unit,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
) {
    NavigationBarItem(
        selected = isSelected,
        onClick = onClick,
        icon = { Icon(navItemData.icon, contentDescription = navItemData.label) },
        modifier = modifier,
    )
}
