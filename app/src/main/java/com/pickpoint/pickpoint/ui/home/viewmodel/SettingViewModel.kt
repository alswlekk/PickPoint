package com.pickpoint.pickpoint.ui.home.viewmodel

import android.app.Application
import android.app.LocaleManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pickpoint.pickpoint.ui.common.util.DataStoreManager
import com.pickpoint.pickpoint.ui.model.setting.LanguageSetting
import com.pickpoint.pickpoint.ui.model.setting.ThemeSetting
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class SettingViewModel(
    private val application: Application,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {

    private val _themeSettingIndex = MutableStateFlow<Int>(0)
    val themeSettingIndex: StateFlow<Int> = _themeSettingIndex

    private val _languageSettingIndex = MutableStateFlow<Int>(0)
    val languageSettingIndex: StateFlow<Int> = _languageSettingIndex

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private val localeManager = application.getSystemService(LocaleManager::class.java)

    init {
        loadSettings()
    }

    // reset 버튼의 onClick 이벤트로 설정하면 됨.
    fun resetSettings() {
        loadSettings()
    }

    // confirm 버튼의 onClick 이벤트로 설정하면 됨.
    fun saveSettings() {
        viewModelScope.launch {
            // api 33 이상일 경우
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

                val languageTag = when (_languageSettingIndex.value) {
                    LanguageSetting.KOREAN.index -> "ko"
                    LanguageSetting.ENGLISH.index -> "en"
                    LanguageSetting.JAPANESE.index -> "ja"
                    else -> "ko"
                }

                AppCompatDelegate.setApplicationLocales(
                    LocaleListCompat.forLanguageTags(languageTag)
                )
            }

            dataStoreManager.saveAllSettings(
                when (_themeSettingIndex.value) {
                    ThemeSetting.PROTOTYPE.index -> ThemeSetting.PROTOTYPE
                    ThemeSetting.COMING_SOON.index -> ThemeSetting.COMING_SOON
                    else -> ThemeSetting.PROTOTYPE
                },
                when (_languageSettingIndex.value) {
                    LanguageSetting.KOREAN.index -> LanguageSetting.KOREAN
                    LanguageSetting.ENGLISH.index -> LanguageSetting.ENGLISH
                    LanguageSetting.JAPANESE.index -> LanguageSetting.JAPANESE
                    else -> LanguageSetting.KOREAN
                },
            )
        }
    }


    private fun loadSettings() {
        viewModelScope.launch {
            combine(
                dataStoreManager.getThemeSetting(),
                dataStoreManager.getLanguageSetting(),
            ) { theme, language ->
                Pair(theme, language)
            }.collect { (theme, language) ->
                when (theme) {
                    ThemeSetting.PROTOTYPE -> {
                        _themeSettingIndex.value = ThemeSetting.PROTOTYPE.index
                    }

                    ThemeSetting.COMING_SOON -> {
                        _themeSettingIndex.value = ThemeSetting.COMING_SOON.index
                    }

                }

                when (language) {
                    LanguageSetting.KOREAN -> {
                        _languageSettingIndex.value = LanguageSetting.KOREAN.index
                    }

                    LanguageSetting.ENGLISH -> {
                        _languageSettingIndex.value = LanguageSetting.ENGLISH.index
                    }

                    LanguageSetting.JAPANESE -> {
                        _languageSettingIndex.value = LanguageSetting.JAPANESE.index
                    }
                }

            }
        }
    }

    fun updateThemeSettingIndex(index: Int) {
        _themeSettingIndex.value = index
    }

    fun updateLanguageSettingIndex(index: Int) {
        _languageSettingIndex.value = index
    }
}