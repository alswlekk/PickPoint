package com.pickpoint.pickpoint.navigation

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.pickpoint.pickpoint.ui.common.util.DataStoreManager
import com.pickpoint.pickpoint.ui.home.screen.HomeScreen
import com.pickpoint.pickpoint.ui.home.screen.SettingRoute
import com.pickpoint.pickpoint.ui.home.viewmodel.SettingViewModel
import com.pickpoint.pickpoint.ui.randompicker.screen.RandomPickerScreen
import com.pickpoint.pickpoint.ui.teammaker.screen.TeamMakerScreen
import com.pickpoint.pickpoint.ui.theme.AppTheme
import com.pickpoint.pickpoint.ui.whattodo.screen.WhatToDoScreen
import androidx.core.net.toUri
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pickpoint.pickpoint.ui.home.screen.QRCodeScreen

@Composable
fun PickPointNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    dataStoreManager: DataStoreManager,
    changeTheme: (AppTheme) -> Unit = {}
) {

    val context = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = Routes.Home.route
    ) {
        composable(route = Routes.Home.route) {
            HomeScreen(
                modifier = modifier,
                onNavigateToSettings = { navController.navigate(Routes.Settings.route) },
                onNavigateToReport = {
                    val intent = Intent(
                        Intent.ACTION_VIEW,
                        "https://play.google.com/store/apps/details?id=com.pickpoint.pickpoint".toUri()
                    )
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    context.startActivity(intent)
                },
                onNavigateToRandomPicker = { navController.navigate(Routes.RandomPicker.route) },
                onNavigateToTeamMaker = { navController.navigate(Routes.TeamMaker.route) },
                onNavigateToWhatToDo = { navController.navigate(Routes.WhatToDo.route) },
                onNavigateToQRCode = { navController.navigate(Routes.QRCode.route)}
            )
        }
        composable(route = Routes.Settings.route) {
            val viewModel = SettingViewModel(dataStoreManager = dataStoreManager)
            SettingRoute(
                modifier = modifier,
                viewModel = viewModel,
                onNavigateBack = { navController.navigateUp() },
                changeTheme = changeTheme
            )
        }
        composable(route = Routes.Report.route) {
            //ReportScreen(onNavigateBack = { navController.navigateUp() })
        }
        composable(route = Routes.RandomPicker.route) {
            RandomPickerScreen(
                modifier = modifier,
                onNavigateBack = { navController.navigateUp() }
            )
        }
        composable(route = Routes.TeamMaker.route) {
            TeamMakerScreen(
                modifier = modifier,
                onNavigateBack = { navController.navigateUp() }
            )
        }
        composable(route = Routes.WhatToDo.route) {
            WhatToDoScreen(
                modifier = modifier,
                onNavigateBack = { navController.navigateUp() }
            )
        }
        composable(route = Routes.QRCode.route) {
            val viewModel = SettingViewModel(dataStoreManager = dataStoreManager)
            QRCodeScreen(
                modifier = modifier,
                viewModel = viewModel,
                onNavigateBack = { navController.navigateUp() }
            )
        }
    }
}