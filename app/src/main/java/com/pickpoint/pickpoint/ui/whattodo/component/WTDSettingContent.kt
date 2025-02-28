package com.pickpoint.pickpoint.ui.whattodo.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pickpoint.pickpoint.ui.common.component.NumberSettingComponent
import com.pickpoint.pickpoint.ui.common.component.ResetConfirmButton
import com.pickpoint.pickpoint.ui.common.component.ResultsComponent
import com.pickpoint.pickpoint.ui.theme.AppTheme
import com.pickpoint.pickpoint.ui.theme.PickPointTheme

@Composable
fun WTDSettingContent(
    modifier: Modifier = Modifier,
    count: Int,
    onPlusButtonClick: () -> Unit,
    onMinusButtonClick: () -> Unit,
    resultList: List<String>,
    onResultChanged: (Int, String) -> Unit,
    reset: () -> Unit,
    confirm: () -> Unit
) {
    Box(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
    ) {
        Column {
            NumberSettingComponent(
                modifier = Modifier
                    .padding(top = 30.dp)
                    .padding(horizontal = 20.dp),
                label = "Total Points",
                currentNumber = count,
                onPlusButtonClick = {
                    if (count < 10) onPlusButtonClick()
                },
                onMinusButtonClick = {
                    if (count > 1) onMinusButtonClick()
                }
            )

            ResultsComponent(
                modifier = Modifier
                    .padding(top = 46.dp),
                title = "Results",
                count = count,
                resultList = resultList,
                onResultChanged = { index, result ->
                    onResultChanged(index, result)
                }
            )
        }

        ResetConfirmButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 14.dp)
                .padding(horizontal = 20.dp)
                .align(Alignment.BottomCenter),
            reset = { reset() },
            apply = { confirm() }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun WTDSettingContentPreview() {
    PickPointTheme(theme = AppTheme.LIGHT_PROTOTYPE, dynamicColor = false) {
        WTDSettingContent(
            count = 1,
            onPlusButtonClick = {},
            onMinusButtonClick = {},
            resultList = listOf(
                "벌칙1", "벌칙2", "벌칙3", "벌칙4"
            ),
            onResultChanged = { index, result ->

            },
            reset = {},
            confirm = {}
        )
    }
}