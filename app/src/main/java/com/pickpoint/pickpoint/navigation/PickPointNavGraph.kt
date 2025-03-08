package com.pickpoint.pickpoint.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.pickpoint.pickpoint.ui.home.screen.HomeScreen
import com.pickpoint.pickpoint.ui.home.screen.SettingsScreen
import com.pickpoint.pickpoint.ui.home.viewmodel.HomeViewModel
import com.pickpoint.pickpoint.ui.randompicker.screen.RandomPickerScreen
import com.pickpoint.pickpoint.ui.teammaker.screen.TeamMakerScreen
import com.pickpoint.pickpoint.ui.whattodo.screen.WhatToDoScreen

@Composable
fun PickPointNavGraph(
    navController: NavHostController,
    homeViewModel: HomeViewModel? = null
) {
    NavHost(
        navController = navController,
        startDestination = Routes.Home.route
    ) {
        composable(route = Routes.Home.route) {
            HomeScreen(
                viewModel = homeViewModel,
                onNavigateToSettings = { navController.navigate(Routes.Settings.route) },
                onNavigateToReport = { navController.navigate(Routes.Report.route) },
                onNavigateToRandomPicker = { navController.navigate(Routes.RandomPicker.route) },
                onNavigateToTeamMaker = { navController.navigate(Routes.TeamMaker.route) },
                onNavigateToWhatToDo = { navController.navigate(Routes.WhatToDo.route) }
            )
        }
        composable(route = Routes.Settings.route) {
            SettingsScreen(
                viewModel = homeViewModel,
                onNavigateBack = { navController.navigateUp() }
            )
        }
        composable(route = Routes.Report.route) {
            //ReportScreen(onNavigateBack = { navController.navigateUp() })
        }
        composable(route = Routes.RandomPicker.route) {
            RandomPickerScreen(
                onNavigateBack = { navController.navigateUp() }
            )
        }
        composable(route = Routes.TeamMaker.route) {
            TeamMakerScreen(
                onNavigateBack = { navController.navigateUp() }
            )
        }
        composable(route = Routes.WhatToDo.route) {
            WhatToDoScreen(
                onNavigateBack = { navController.navigateUp() }
            )
        }
    }
}