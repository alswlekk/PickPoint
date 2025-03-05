package com.pickpoint.pickpoint.ui.randompicker.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.pickpoint.pickpoint.R
import com.pickpoint.pickpoint.ui.common.component.CircleButton
import com.pickpoint.pickpoint.ui.common.util.getPointColorList
import com.pickpoint.pickpoint.ui.common.util.timerStartHandler
import com.pickpoint.pickpoint.ui.theme.AppTheme
import com.pickpoint.pickpoint.ui.theme.LocalPointColors
import com.pickpoint.pickpoint.ui.theme.PickPointTheme
import kotlinx.coroutines.delay
import kotlin.math.roundToInt


@Composable
fun RandomPickerGameComponent(
    modifier: Modifier = Modifier,
    pointsToSelect: Int = 1, // 최종적으로 선택할 점의 개수
    resultDialog: @Composable ((onRetry: () -> Unit) -> Unit)? = null, // 카운트다운 끝난 후 결과 다이얼로그
) {
    val pointColorList = LocalPointColors.current.getPointColorList()
    val pointSize = 100
    val timeToStart: Long = 2000 //2초
    val usedColors = remember { mutableStateListOf<Color>() }
    var countdown by remember { mutableStateOf<Int?>(null) }
    var isGameActive by remember { mutableStateOf(true) } // 게임 진행 여부
    var showResultDialog by remember { mutableStateOf(false) } // 결과 다이얼로그 표시 여부

    val (touchPoints, finalPoints) = timerStartHandler(
        pointsToStart = pointsToSelect + 1,
        timeToStart = timeToStart,
    )

    val resultPoints = remember { mutableStateListOf<Pair<Offset, Color>>() }

    // 카운트다운
    LaunchedEffect(touchPoints.keys.toSet()) {
        countdown = null
        if (touchPoints.size > pointsToSelect) {
            delay(timeToStart)
            for (i in 3 downTo 1) {
                countdown = i
                delay(1000)
            }
            isGameActive = false
            countdown = null
            showResultDialog = true

            finalPoints.clear()
            finalPoints.addAll(touchPoints.values)

            // 로직 반영
            resultPoints.clear()
            resultPoints.addAll(finalPoints.shuffled().take(pointsToSelect))
        }
    }

    val resetGame: () -> Unit = {
        isGameActive = true
        showResultDialog = false
        countdown = null
        touchPoints.clear()
        finalPoints.clear()
        resultPoints.clear()
        usedColors.clear()
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            // pointerInput을 이용해 터치 이벤트를 감지
            .pointerInput(Unit) {
                awaitPointerEventScope {
                    while (true) {
                        val event = awaitPointerEvent()
                        event.changes.forEach { pointerInputChange ->
                            val pointerId = pointerInputChange.id.value
                            if (pointerInputChange.pressed) {
                                // 이미 있는 포인터면 색상 유지, 없으면 랜덤 색상 할당
                                if (pointerId !in touchPoints) {
                                    val availableColor =
                                        pointColorList.filter { it !in usedColors }.randomOrNull()
                                    if (availableColor != null) {
                                        usedColors.add(availableColor)
                                        touchPoints[pointerId] =
                                            pointerInputChange.position to availableColor
                                    }
                                } else {
                                    //기존 위치 업데이트
                                    touchPoints[pointerId] =
                                        pointerInputChange.position to touchPoints[pointerId]!!.second
                                }
                            } else {
                                touchPoints[pointerId]?.second?.let { usedColors.remove(it) }
                                touchPoints.remove(pointerId)
                            }
                        }
                    }
                }
            }
    ) {
        // 현재 활성화된 각 터치에 대해 Point composable 표시
        if (isGameActive) {
            touchPoints.forEach { (_, data) ->
                val (position, color) = data
                // offset을 이용해 터치한 위치에 Point를 배치
                CircleButton(
                    modifier = Modifier.offset {
                        IntOffset(
                            (position.x - (pointSize / 2).dp.toPx()).roundToInt(),
                            (position.y - (pointSize / 2).dp.toPx()).roundToInt()
                        )
                    },
                    pointSize = pointSize,
                    color = color,
                )
            }
        }
        // 카운트다운 끝난 후 결과 Point 표시
        resultPoints.forEach { (position, color) ->
            CircleButton(
                modifier = Modifier.offset {
                    IntOffset(
                        (position.x - (pointSize / 2).dp.toPx()).roundToInt(),
                        (position.y - (pointSize / 2).dp.toPx()).roundToInt()
                    )
                },
                pointSize = pointSize,
                color = color,
            )
        }
        // 카운트다운 표시
        countdown?.let {
            Text(
                text = it.toString(),
                style = MaterialTheme.typography.displayLarge,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        if (showResultDialog) {
            resultDialog?.invoke(resetGame)
        }
    }

}


@Preview(showBackground = true)
@Composable
private fun RandomPickerGameComponentPreview() {
//    val appTheme: AppTheme = AppTheme.LIGHT_PROTOTYPE
    val appTheme: AppTheme = AppTheme.DARK_PROTOTYPE
    PickPointTheme(theme = appTheme, dynamicColor = false) {
        Column(modifier = Modifier.fillMaxSize()) {
            RandomPickerGameComponent(
                resultDialog = { onRetry ->
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Button(
                            onClick = { onRetry() },
                            modifier = Modifier.align(Alignment.Center)
                        ) {
                            Text(
                                stringResource(id = R.string.retry),
                                style = MaterialTheme.typography.labelLarge,
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    }
                }
            )
        }
    }
}