package com.pickpoint.pickpoint.ui.whattodo.viewmodel

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.pickpoint.pickpoint.ui.common.util.getRandomElements
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class WhatToDoViewmodel : ViewModel() {

    private val _count: MutableStateFlow<Int> = MutableStateFlow(4)
    val count: StateFlow<Int> = _count.asStateFlow()

    private val _resultList: MutableStateFlow<List<String>> = MutableStateFlow(
        mutableListOf(
            "", "", "", ""
        )
    )
    val resultList: StateFlow<List<String>> = _resultList.asStateFlow()

    private val _isConfirmed: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isConfirmed: StateFlow<Boolean> = _isConfirmed.asStateFlow()

    private val _randomColors: MutableStateFlow<List<Color>> = MutableStateFlow(emptyList())
    val randomColors: StateFlow<List<Color>> = _randomColors.asStateFlow()

    fun setConfirmed(value: Boolean) {
        _isConfirmed.value = value
    }

    fun initRandomColors(colors : List<Color>){
        _randomColors.value = colors
    }

    fun onConfirmButtonClick() {
        _isConfirmed.value = true
        _randomColors.value = randomColors.value.getRandomElements(count = count.value)
    }

    fun onPlusButtonClick() {
        if (_count.value < 10) {
            _count.value++
            _resultList.value = _resultList.value.toMutableList().apply { add("") }
        }
    }

    fun onMinusButtonClick() {
        if (_count.value > 1) {
            _count.value--
            _resultList.value = _resultList.value.toMutableList().apply { removeAt(_count.value) }
        }
    }

    fun updateResultIndex(index: Int, result: String) {
        _resultList.value = _resultList.value.toMutableList().apply { set(index, result) }
    }

    fun reset() {
        _count.value = 4
        _resultList.value = mutableListOf("", "", "", "")
    }

    fun onSettingClick() {
        _isConfirmed.value = false
        _randomColors.value = randomColors.value.getRandomElements(count = count.value)
    }

}