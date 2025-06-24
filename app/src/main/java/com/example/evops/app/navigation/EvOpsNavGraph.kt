package com.example.evops.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.evops.screens.evendetails.presentation.EventDetailsScreen
import com.example.evops.screens.eventlist.presentation.EventListScreen

@Composable
fun EvOpsNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Route.EventList.route,
        modifier = modifier
    ) {
        composable(route = Route.EventList.route) {
            EventListScreen(navController = navController)
        }
        composable(route = Route.EventDetails.route + "/{eventId}") {
            EventDetailsScreen(navController = navController)
        }
    }
}