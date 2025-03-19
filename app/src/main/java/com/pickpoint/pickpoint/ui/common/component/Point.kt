package com.pickpoint.pickpoint.ui.common.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pickpoint.pickpoint.ui.common.util.getPointColorList
import com.pickpoint.pickpoint.ui.theme.AppTheme
import com.pickpoint.pickpoint.ui.theme.LightPrototypePointColors
import com.pickpoint.pickpoint.ui.theme.LocalPointColors
import com.pickpoint.pickpoint.ui.theme.PickPointTheme

@Composable
fun CircleButton(
    modifier: Modifier = Modifier,
    color: Color,
    pointSize: Int,
    number: Int? = null,
) {
    Box(
        modifier = modifier
            .size(pointSize.dp)
            .background(color = color, shape = CircleShape),
        contentAlignment = Alignment.Center // 텍스트를 중앙 정렬
    ) {
        number?.let {
            Text(
                text = it.toString(),
                style = MaterialTheme.typography.displaySmall,
                color = LocalPointColors.current.pointTextColor,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview
@Composable
fun PointColorPreview() {
    CompositionLocalProvider(LocalPointColors provides LightPrototypePointColors) {
        val colors = LocalPointColors.current.getPointColorList()

        PickPointTheme(theme = AppTheme.LIGHT_PROTOTYPE, dynamicColor = false) {
//            FlowRow(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp),
//                horizontalArrangement = Arrangement.spacedBy(8.dp)
//            ) {
//                colors.forEachIndexed { index, color ->
//                    CircleButton(
//                        color = color,
//                        pointSize = 100,
//                        number = if (index % 2 == 0) index + 1 else null
//                    )
//                }
//            }
        }
    }
}
