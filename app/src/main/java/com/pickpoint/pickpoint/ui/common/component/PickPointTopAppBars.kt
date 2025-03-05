package com.pickpoint.pickpoint.ui.common.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pickpoint.pickpoint.R
import com.pickpoint.pickpoint.ui.theme.*

@Composable
fun MainTopAppBar(
    modifier: Modifier = Modifier,
    title: String,
    leftIcon: @Composable () -> Unit = {},
    leftIconClick: () -> Unit = {},
    rightIcon: @Composable () -> Unit = {},
    rightIconClick: () -> Unit = {},
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        color = MaterialTheme.colorScheme.background,
        shadowElevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = leftIconClick,
                modifier = Modifier
                    .size(24.dp)
            ) {
                leftIcon()
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = title,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.padding(end = 20.dp),
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.weight(1f))
            IconButton(
                onClick = rightIconClick,
                modifier = Modifier
                    .size(24.dp)
            ) {
                rightIcon()
            }
        }
    }
}

@Composable
fun SecondaryTopAppBar(
    modifier: Modifier = Modifier,
    title: String,
    onNavigationClick: () -> Unit
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        color = MaterialTheme.colorScheme.background,
        shadowElevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            IconButton(
                onClick = onNavigationClick,
                modifier = Modifier.size(24.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.secondary
                )
            }
            Text(
                text = title,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.padding(start = 18.dp),
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}

@Composable
fun RandomPickerTopAppBar(
    modifier: Modifier = Modifier,
    title : String,
    onBackClick : () -> Unit,
    onSettingClick : () -> Unit
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        color = MaterialTheme.colorScheme.background,
        shadowElevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier.size(24.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.secondary
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = title,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.padding(end = 20.dp),
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.weight(1f))
            IconButton(
                onClick = onSettingClick,
                modifier = Modifier
                    .size(48.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_main_top_setting),
                    contentDescription = "Setting",
                    tint = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainTopAppBarPreview() {
    PickPointTheme(theme = AppTheme.LIGHT_PROTOTYPE, dynamicColor = false) {
        MainTopAppBar(
            title = "Pick Point",
            leftIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_main_top_back),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.secondary
                )
            },
            rightIcon = {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.secondary
                )
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SecondaryTopAppBarPreview() {
    PickPointTheme(theme = AppTheme.LIGHT_PROTOTYPE, dynamicColor = false) {
        SecondaryTopAppBar(title = "Settings", onNavigationClick = {})
    }
}

@Preview(showBackground = true)
@Composable
fun RandomPickerTopAppBarPreview() {
    PickPointTheme(theme = AppTheme.LIGHT_PROTOTYPE, dynamicColor = false) {
        RandomPickerTopAppBar(
            title = "Random Picker",
            onBackClick = {},
            onSettingClick = {}
        )
    }
}

