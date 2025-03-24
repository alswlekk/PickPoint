package com.pickpoint.pickpoint.ui.common.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.pickpoint.pickpoint.R
import com.pickpoint.pickpoint.ui.theme.AppTheme
import com.pickpoint.pickpoint.ui.theme.PickPointTheme

@Composable
fun TapToStartComponent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.tap_to_start),
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.displayLarge,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TapToStartComponentPreview() {
    PickPointTheme(theme = AppTheme.LIGHT_PROTOTYPE, dynamicColor = false) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TapToStartComponent()
        }
    }
}