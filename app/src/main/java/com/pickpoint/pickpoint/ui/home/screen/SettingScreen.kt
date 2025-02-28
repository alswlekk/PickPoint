package com.pickpoint.pickpoint.ui.home.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pickpoint.pickpoint.R
import com.pickpoint.pickpoint.ui.common.component.SecondaryTopAppBar
import com.pickpoint.pickpoint.ui.common.component.SettingComponent
import com.pickpoint.pickpoint.ui.home.viewmodel.HomeViewModel
import com.pickpoint.pickpoint.ui.model.setting.LanguageSetting
import com.pickpoint.pickpoint.ui.model.setting.PreferencesSetting
import com.pickpoint.pickpoint.ui.model.setting.ThemeSetting
import com.pickpoint.pickpoint.ui.common.component.ResetConfirmButton
import com.pickpoint.pickpoint.ui.theme.AppTheme
import com.pickpoint.pickpoint.ui.theme.LightPrototypeBackgroundColor
import com.pickpoint.pickpoint.ui.theme.PickPointTheme

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel? = null,
    onNavigateBack: () -> Unit = {}
) {

    val themeIndex = viewModel!!.themeSettingIndex.collectAsState()
    val languageIndex = viewModel.languageSettingIndex.collectAsState()
    val preferencesIndex = viewModel.preferencesSettingIndex.collectAsState()

    Surface(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize()
        ) {
            SecondaryTopAppBar(
                title = stringResource(R.string.settings),
                onNavigationClick = onNavigateBack
            )
            Spacer(modifier = Modifier.padding(15.dp))

            //테마 설정
            SettingComponent(
                title = stringResource(R.string.theme),
                settingRes = ThemeSetting.entries.map { it.res },
                //checkedIndex = 0,
                checkedIndex = themeIndex.value,
                onClick = { viewModel.updateThemeSettingIndex(it) }
            )
            Spacer(modifier = Modifier.padding(15.dp))

            //언어 설정
            SettingComponent(
                title = stringResource(R.string.language),
                settingRes = LanguageSetting.entries.map { it.res },
                checkedIndex = languageIndex.value,
                onClick = { viewModel.updateLanguageSettingIndex(it) }
            )
            Spacer(modifier = Modifier.padding(15.dp))

            // 추가 설정
            SettingComponent(
                title = stringResource(R.string.preferences),
                settingRes = PreferencesSetting.entries.map { it.res },
                checkedIndex = preferencesIndex.value,
                onClick = { viewModel.updatePreferencesSettingIndex(it) }
            )
            Spacer(modifier = Modifier.paddingFromBaseline(top = 124.dp))

            // 리셋 및 확인 버튼
            ResetConfirmButton(
                reset = { viewModel.resetSettings() },
                apply = { viewModel.saveSettings() }
            )

            Spacer(modifier = Modifier.padding(15.dp))
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    PickPointTheme(theme = AppTheme.LIGHT_PROTOTYPE, dynamicColor = false) {
        SettingsScreen()
    }
}