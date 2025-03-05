package com.pickpoint.pickpoint.ui.randompicker.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import com.pickpoint.pickpoint.ui.common.component.MainTopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.pickpoint.pickpoint.R
import com.pickpoint.pickpoint.ui.common.component.SecondaryTopAppBar
import com.pickpoint.pickpoint.ui.randompicker.component.RandomPickerGameComponent
import com.pickpoint.pickpoint.ui.randompicker.component.RandomPickerSettingContent
import com.pickpoint.pickpoint.ui.teammaker.component.TeamMakerGameComponent
import com.pickpoint.pickpoint.ui.teammaker.component.TeamMakerSettingContent
import com.pickpoint.pickpoint.ui.teammaker.component.TeamMakerTryAgain
import com.pickpoint.pickpoint.ui.theme.AppTheme
import com.pickpoint.pickpoint.ui.theme.LightPrototypeOnPrimaryColor
import com.pickpoint.pickpoint.ui.theme.PickPointTheme

@Composable
fun RandomPickerScreen(
    onNavigateBack: () -> Unit
) {
    var totalCount by remember { mutableIntStateOf(4) }
    var pointsToPick by remember { mutableIntStateOf(1) }
    var confirmed by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.background(MaterialTheme.colorScheme.background),
        topBar = {
            if (confirmed) {
                MainTopAppBar(
                    title = stringResource(id = R.string.random_picker),
                    leftIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_main_top_back),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    },
                    rightIcon = {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                )
            } else {
                SecondaryTopAppBar(
                    title = stringResource(id = R.string.game_settings),
                    onNavigationClick = onNavigateBack
                )
            }
        },
    ) { innerPadding ->
        if (!confirmed) {
            RandomPickerSettingContent(
                modifier = Modifier.padding(innerPadding),
                pointsToPick = pointsToPick,
                pointsToPickPlus = {
                    if (pointsToPick < 10) pointsToPick += 1
                },
                pointsToPickMinus = {
                    if (pointsToPick > 1) pointsToPick -= 1
                },
                reset = {
                    totalCount = 4
                    pointsToPick = 1
                },
                apply = { confirmed = true }
            )
        } else {
            RandomPickerGameComponent(
                modifier = Modifier.padding(innerPadding),
                pointsToSelect = pointsToPick,
                resultDialog = { onRetry ->
                    TeamMakerTryAgain {
                        onRetry()
                        confirmed = false
                    }
                }
            )
        }
    }
}

@Preview
@Composable
fun RandomPickerScreenPreview() {
    PickPointTheme(theme = AppTheme.LIGHT_PROTOTYPE, dynamicColor = false) {
        RandomPickerScreen { }
    }
}
