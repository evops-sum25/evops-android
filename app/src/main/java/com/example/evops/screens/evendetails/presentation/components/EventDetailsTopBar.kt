package com.example.evops.screens.evendetails.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.evops.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventDetailsTopBar(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(4.dp)
    ) {
        NavigateBackButton(onClick = { navController.popBackStack() })
        ScreenTitle()
    }
}

@Composable
private fun ScreenTitle(modifier: Modifier = Modifier) {
    Text(
        text = stringResource(R.string.event_details),
        style = MaterialTheme.typography.displayMedium,
        modifier = modifier
    )
}

@Composable
private fun NavigateBackButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
            contentDescription = stringResource(R.string.description_navigate_back_icon),
        )
    }
}

@Preview
@Composable
private fun EventDetailsTopBarPreview() {
    EventDetailsTopBar(
        navController = rememberNavController(),
        modifier = Modifier.fillMaxWidth()
    )
}
