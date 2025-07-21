package com.example.evops.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.evops.core.navigation.NavAnimationUtils.slideInToLeft
import com.example.evops.core.navigation.NavAnimationUtils.slideOutToRight
import com.example.evops.screens.auth.presentation.AuthScreen
import com.example.evops.screens.createevent.presentation.CreateEventScreen
import com.example.evops.screens.evendetails.presentation.EventDetailsScreen
import com.example.evops.screens.eventlist.presentation.EventListScreen
import com.example.evops.screens.settings.presentation.SettingsScreen

@Composable
fun EvopsNavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController = navController, startDestination = SubGraph.Home, modifier = modifier) {
        navigation<SubGraph.Auth>(startDestination = Destination.Auth) {
            composable<Destination.Auth> { AuthScreen(navController = navController) }
        }
        navigation<SubGraph.Home>(startDestination = Destination.EventList) {
            composable<Destination.EventList> { EventListScreen(navController = navController) }
            composable<Destination.EventDetails>(
                enterTransition = ::slideInToLeft,
                exitTransition = ::slideOutToRight,
            ) {
                EventDetailsScreen(navController = navController)
            }
        }
        navigation<SubGraph.CreateEvent>(startDestination = Destination.CreateEvent) {
            composable<Destination.CreateEvent> { CreateEventScreen() }
        }
        navigation<SubGraph.Settings>(startDestination = Destination.Settings) {
            composable<Destination.Settings> { SettingsScreen() }
        }
    }
}
