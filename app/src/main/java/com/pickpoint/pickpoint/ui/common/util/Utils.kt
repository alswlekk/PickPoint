package com.pickpoint.pickpoint.ui.common.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import com.pickpoint.pickpoint.ui.theme.PointColors
import kotlinx.coroutines.delay

// Point 색상을 담은 리스트를 반환하는 함수
fun PointColors.getPointColorList(): List<Color>{
    return listOf(
        pointColor1, pointColor2, pointColor3, pointColor4, pointColor5,
        pointColor6, pointColor7, pointColor8, pointColor9, pointColor10
    )
}


fun <T> List<T>.getRandomElements(count: Int): List<T> {
    val shuffledList = this.shuffled()
    if(count > shuffledList.size) return shuffledList

    return shuffledList.subList(0, count)
}

// WTD 결과를 string으로 변경
fun List<String>.getResultString(): String{
    if (this.isEmpty()) return ""

    var result = "Results\n\n"
    for (i in indices) {
        if (i == this.size - 1){
            // 마지막에는 \n 추가 안함
            result += "${i+1}. "+this[i]
        }else{
            result += "${i+1}. "+this[i]+"\n"
        }
    }
    return result
}