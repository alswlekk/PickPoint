package com.pickpoint.pickpoint.ui.home.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pickpoint.pickpoint.R
import com.pickpoint.pickpoint.ui.common.component.SecondaryTopAppBar
import com.pickpoint.pickpoint.ui.common.component.SettingComponent
import com.pickpoint.pickpoint.ui.home.viewmodel.SettingViewModel
import com.pickpoint.pickpoint.ui.model.setting.LanguageSetting
import com.pickpoint.pickpoint.ui.model.setting.PointThemeSetting
import com.pickpoint.pickpoint.ui.common.component.ResetConfirmButton
import com.pickpoint.pickpoint.ui.common.component.SettingSwitch
import com.pickpoint.pickpoint.ui.theme.AppTheme

@Composable
fun SettingRoute(
    viewModel: SettingViewModel,
    onNavigateBack: () -> Unit = {},
    changeTheme: (AppTheme) -> Unit = {}
) {
    val appTheme = viewModel.appThemeSetting.collectAsStateWithLifecycle()
    val pointThemeIndex = viewModel.pointThemeSettingIndex.collectAsStateWithLifecycle()
    val languageIndex = viewModel.languageSettingIndex.collectAsStateWithLifecycle()

    SettingsScreen(
        appTheme = appTheme.value,
        pointThemeIndex = pointThemeIndex.value,
        languageIndex = languageIndex.value,
        onNavigateBack = onNavigateBack,
        changeTheme = changeTheme,
        reverseAppThemeSetting = { viewModel.reverseAppThemeSetting() },
        updatePointThemeSettingIndex = { viewModel.updatePointThemeSettingIndex(it) },
        updateLanguageSettingIndex = { viewModel.updateLanguageSettingIndex(it) },
        resetSettings = { viewModel.resetSettings() },
        saveSettings = { viewModel.saveSettings() }
    )
}

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    appTheme: AppTheme,
    pointThemeIndex: Int,
    languageIndex: Int,
    onNavigateBack: () -> Unit = {},
    changeTheme: (AppTheme) -> Unit = {},
    reverseAppThemeSetting: () -> Unit = {},
    updatePointThemeSettingIndex: (Int) -> Unit = {},
    updateLanguageSettingIndex: (Int) -> Unit = {},
    resetSettings: () -> Unit = {},
    saveSettings: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .verticalScroll(rememberScrollState())
        ) {
            SecondaryTopAppBar(
                title = stringResource(id = R.string.settings),
                onNavigationClick = onNavigateBack
            )
            Spacer(modifier = Modifier.padding(15.dp))

            // App Theme 설정
            SettingSwitch(
                title = stringResource(id = R.string.app_theme),
                mode = appTheme,
                onClick = { reverseAppThemeSetting()}
            )
            Spacer(modifier = Modifier.padding(15.dp))

            // Point 설정
            SettingComponent(
                title = stringResource(id = R.string.point_theme),
                settingRes = PointThemeSetting.entries.map { it.res },
                checkedIndex = pointThemeIndex,
                onClick = { updatePointThemeSettingIndex(it) }
            )
            Spacer(modifier = Modifier.padding(15.dp))

            // 언어 설정
            SettingComponent(
                title = stringResource(id = R.string.language),
                settingRes = LanguageSetting.entries.map { it.res },
                checkedIndex = languageIndex,
                onClick = { updateLanguageSettingIndex(it) }
            )

            Spacer(modifier = Modifier.paddingFromBaseline(top = 124.dp))

        }
        // 리셋 및 확인 버튼
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 20.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            ResetConfirmButton(
                modifier = modifier
                    .padding(horizontal = 20.dp),
                reset = { resetSettings() },
                apply = {
                    changeTheme(appTheme)
                    saveSettings()
                }
            )
        }
    }
}

