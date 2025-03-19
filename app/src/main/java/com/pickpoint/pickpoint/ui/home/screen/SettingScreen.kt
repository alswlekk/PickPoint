package com.pickpoint.pickpoint.ui.home.screen

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pickpoint.pickpoint.R
import com.pickpoint.pickpoint.ui.common.component.SecondaryTopAppBar
import com.pickpoint.pickpoint.ui.common.component.SettingComponent
import com.pickpoint.pickpoint.ui.home.viewmodel.SettingViewModel
import com.pickpoint.pickpoint.ui.model.setting.LanguageSetting
import com.pickpoint.pickpoint.ui.model.setting.PointThemeSetting
import com.pickpoint.pickpoint.ui.common.component.ResetConfirmButton
import com.pickpoint.pickpoint.ui.theme.AppTheme
import com.pickpoint.pickpoint.ui.theme.PickPointTheme

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    viewModel: SettingViewModel? = null,
    onNavigateBack: () -> Unit = {}
) {

    val themeIndex = viewModel!!.themeSettingIndex.collectAsStateWithLifecycle()
    val languageIndex = viewModel.languageSettingIndex.collectAsStateWithLifecycle()

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



            // Point 설정
            SettingComponent(
                title = stringResource(id = R.string.theme),
                settingRes = PointThemeSetting.entries.map { it.res },
                checkedIndex = themeIndex.value,
                onClick = { viewModel.updateThemeSettingIndex(it) }
            )
            Spacer(modifier = Modifier.padding(15.dp))

            // 언어 설정
            SettingComponent(
                title = stringResource(id = R.string.language),
                settingRes = LanguageSetting.entries.map { it.res },
                checkedIndex = languageIndex.value,
                onClick = { viewModel.updateLanguageSettingIndex(it) }
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
                reset = { viewModel.resetSettings() },
                apply = { viewModel.saveSettings() }
            )
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