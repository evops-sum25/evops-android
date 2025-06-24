package com.example.evops.screens.evendetails.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.evops.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventDetailsTopBar(navController: NavController, modifier: Modifier = Modifier) {
    TopAppBar(
        title = { ScreenTitle() },
        navigationIcon = {
            NavigateBackButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier.padding(4.dp)
            )
        },
        modifier = modifier
    )
}

@Composable
private fun ScreenTitle(modifier: Modifier = Modifier) {
    Text(text = stringResource(R.string.event_details), modifier = modifier)
}

@Composable
private fun NavigateBackButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Icon(
        imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
        contentDescription = stringResource(R.string.description_navigate_back_icon),
        modifier = modifier.clickable(onClick = onClick)
    )
}

@Preview
@Composable
private fun EventDetailsTopBarPreview() {
    EventDetailsTopBar(navController = rememberNavController())
}