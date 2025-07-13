package com.example.evops.core.presentation.components.topbar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
fun TitledTopBar(
    title: String,
    modifier: Modifier = Modifier,
    navController: NavController? = null,
) {
    TopAppBar(
        title = { ScreenTitle(title = title) },
        navigationIcon = {
            navController?.let {
                NavigateBackButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.padding(4.dp),
                )
            }
        },
        modifier = modifier,
    )
}

@Composable
private fun ScreenTitle(title: String, modifier: Modifier = Modifier) {
    Text(text = title, style = MaterialTheme.typography.displayMedium, modifier = modifier)
}

@Composable
private fun NavigateBackButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
    IconButton(onClick = onClick, modifier = modifier) {
        Icon(
            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
            contentDescription = stringResource(R.string.description_navigate_back_icon),
        )
    }
}

@Preview
@Composable
private fun EventDetailsTopBarPreview() {
    TitledTopBar(
        title = stringResource(R.string.event_details),
        navController = rememberNavController(),
        modifier = Modifier.fillMaxWidth(),
    )
}
