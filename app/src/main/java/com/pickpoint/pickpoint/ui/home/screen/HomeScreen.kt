package com.pickpoint.pickpoint.ui.home.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pickpoint.pickpoint.ui.home.component.HomeButton
import com.pickpoint.pickpoint.ui.home.viewmodel.HomeViewModel
import com.pickpoint.pickpoint.ui.theme.PickPointTheme
import androidx.compose.runtime.*
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.pickpoint.pickpoint.R
import com.pickpoint.pickpoint.ui.common.component.MainTopAppBar
import com.pickpoint.pickpoint.ui.home.component.TopMenu
import com.pickpoint.pickpoint.ui.theme.AppTheme
import com.pickpoint.pickpoint.ui.theme.LightPrototypeBackgroundColor


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel? = null,
    onNavigateToSettings: () -> Unit = {},
    onNavigateToReport: () -> Unit = {},
    onNavigateToRandomPicker: () -> Unit = {},
    onNavigateToTeamMaker: () -> Unit = {},
    onNavigateToWhatToDo: () -> Unit = {}
) {
    var menuExpanded by remember { mutableStateOf(false) }

    Box(modifier = modifier
        .fillMaxSize()
        .clickable(
            indication = null,
            interactionSource = remember { MutableInteractionSource() }
        ) { menuExpanded = false }
        .background(LightPrototypeBackgroundColor)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background),
            ) {
                MainTopAppBar(
                    title = stringResource(R.string.pick_point),
                    leftIcon = {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = stringResource(R.string.menu),
                            tint = MaterialTheme.colorScheme.secondary
                        )
                    },
                    leftIconClick = { menuExpanded = !menuExpanded }
                )

                Spacer(modifier = Modifier.weight(1f))

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = modifier
                ) {
                    HomeButton(
                        onClick = { onNavigateToRandomPicker() },
                        stringResource(R.string.random_picker)
                    )
                    Spacer(modifier = Modifier.padding(24.dp))
                    HomeButton(
                        onClick = { onNavigateToTeamMaker() },
                        stringResource(R.string.team_maker)
                    )
                    Spacer(modifier = Modifier.padding(24.dp))
                    HomeButton(
                        onClick = { onNavigateToWhatToDo() },
                        stringResource(R.string.what_to_do)
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
            }
        }

        // TopMenu as dropdown
        AnimatedVisibility(
            visible = menuExpanded,
            enter = slideInVertically() + fadeIn(),
            exit = slideOutVertically() + fadeOut(),
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(top = 64.dp, start = 12.dp)
        ) {
            TopMenu(
                onReportClick = {
                    onNavigateToReport()
                    menuExpanded = false
                },
                onSettingsClick = {
                    onNavigateToSettings()
                    menuExpanded = false
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeSectionPreview() {
    PickPointTheme(theme = AppTheme.LIGHT_PROTOTYPE, dynamicColor = false) {
        HomeScreen()
    }
}