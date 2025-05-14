package com.pickpoint.pickpoint.ui.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pickpoint.pickpoint.R
import com.pickpoint.pickpoint.ui.theme.AppTheme
import com.pickpoint.pickpoint.ui.theme.LightPrototypePrimaryColor
import com.pickpoint.pickpoint.ui.theme.PickPointTheme

@Composable
fun TopMenu(
    modifier: Modifier = Modifier,
    onReportClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
    Surface(
        modifier = modifier.size(130.dp),
        shape = MaterialTheme.shapes.large,
        shadowElevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primary)
                .padding(vertical = 9.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NavigationItem(
                icon = Icons.Filled.Settings,
                text = stringResource(R.string.settings),
                onClick = onSettingsClick
            )
            NavigationItem(
                icon = Icons.Default.Star,
                text = stringResource(R.string.review),
                onClick = onReportClick
            )
        }
    }
}

@Composable
private fun NavigationItem(
    icon: ImageVector,
    text: String,
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(horizontal = 14.dp, vertical = 7.dp)
            .size(101.dp, 46.dp)
            .fillMaxWidth()
    ) {
        Icon(
            imageVector = icon,
            contentDescription = text,
            tint = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.size(18.dp)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Preview(
    name = "Navigation Drawer",
    showBackground = true,
)
@Composable
fun NavigationDrawerPreview() {
    PickPointTheme(theme = AppTheme.LIGHT_PROTOTYPE, dynamicColor = false) {
        Surface {
            TopMenu(
                onReportClick = { },
                onSettingsClick = { }
            )
        }
    }
}

@Preview(
    name = "Navigation Item",
    showBackground = true,
)
@Composable
fun NavigationItemPreview() {
    PickPointTheme(theme = AppTheme.LIGHT_PROTOTYPE, dynamicColor = false) {
        Surface {
            NavigationItem(
                icon = Icons.Default.Info,
                text = "Sample Item",
                onClick = { }
            )
        }
    }
}