package com.pickpoint.pickpoint.ui.home.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pickpoint.pickpoint.R
import com.pickpoint.pickpoint.ui.common.component.SettingsTopAppBar
import com.pickpoint.pickpoint.ui.home.viewmodel.SettingViewModel
import com.pickpoint.pickpoint.ui.theme.AppTheme
import com.pickpoint.pickpoint.ui.theme.PickPointTheme


@Composable
fun QRCodeScreen(
    modifier: Modifier = Modifier,
    viewModel: SettingViewModel,
    onNavigateBack: () -> Unit = {},
) {
    val appTheme = viewModel.appThemeSetting.collectAsStateWithLifecycle()

    val imageRes = if (appTheme.value == AppTheme.DARK_PROTOTYPE) {
        R.drawable.img_pickpoint_qrcode_dark
    } else {
        R.drawable.img_pickpoint_qrcode_light
    }

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
            SettingsTopAppBar(
                title = stringResource(id = R.string.app_qr_code),
                onNavigationClick = onNavigateBack
            )

            Text(
                text = stringResource(id = R.string.app_qr_code_desc),
                style = MaterialTheme.typography.displayLarge,
                color = MaterialTheme.colorScheme.secondary,
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(top = 20.dp, bottom = 20.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Image(
                painter = painterResource(id = imageRes),
                contentDescription = "pick point QR Code",
                modifier = Modifier
                    .size(350.dp)
                    .padding(bottom = 20.dp)
                    .align(Alignment.CenterHorizontally),
            )
        }
    }
}