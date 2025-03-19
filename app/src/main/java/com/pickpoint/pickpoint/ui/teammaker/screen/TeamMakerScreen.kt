package com.pickpoint.pickpoint.ui.teammaker.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.pickpoint.pickpoint.R
import com.pickpoint.pickpoint.ui.common.component.RandomPickerTopAppBar
import com.pickpoint.pickpoint.ui.common.component.SecondaryTopAppBar
import com.pickpoint.pickpoint.ui.teammaker.component.TeamMakerGameComponent
import com.pickpoint.pickpoint.ui.teammaker.component.TeamMakerSettingContent
import com.pickpoint.pickpoint.ui.teammaker.component.TeamMakerTryAgain
import com.pickpoint.pickpoint.ui.theme.AppTheme
import com.pickpoint.pickpoint.ui.theme.PickPointTheme

@Composable
fun TeamMakerScreen(
    modifier: Modifier = Modifier,
    onNavigateBack: () -> Unit,
) {

    var totalCount by remember { mutableIntStateOf(4) }
    var pointsToPick by remember { mutableIntStateOf(2) }
    var confirmed by remember { mutableStateOf(false) }

    Scaffold(
        modifier = modifier,
        topBar = {
            if (confirmed) {
                RandomPickerTopAppBar(
                    title = stringResource(id = R.string.team_maker),
                    onBackClick = onNavigateBack,
                    onSettingClick = {
                        confirmed = false
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
            TeamMakerSettingContent(
                modifier = Modifier.padding(innerPadding),
                pointsToPick = pointsToPick,
                pointsToPickPlus = {
                    if (pointsToPick < 10) pointsToPick += 1
                },
                pointsToPickMinus = {
                    if (pointsToPick > 2) pointsToPick -= 1
                },
                reset = {
                    totalCount = 4
                    pointsToPick = 2
                },
                apply = { confirmed = true }
            )
        } else {
            TeamMakerGameComponent(
                modifier = Modifier.padding(innerPadding),
                totalTeams = pointsToPick,
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
fun SettingScreenPreview() {
    PickPointTheme(theme = AppTheme.LIGHT_PROTOTYPE, dynamicColor = false) {
        TeamMakerScreen(onNavigateBack = {})
    }
}
