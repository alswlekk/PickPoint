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
import com.pickpoint.pickpoint.ui.model.setting.PointThemeSetting
import com.pickpoint.pickpoint.ui.theme.AppTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class SettingViewModel(
    private val application: Application,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {

    private val _appThemeSetting = MutableStateFlow(AppTheme.LIGHT_PROTOTYPE)
    val appThemeSetting: StateFlow<AppTheme> = _appThemeSetting

    private val _pointThemeSettingIndex = MutableStateFlow<Int>(0)
    val pointThemeSettingIndex: StateFlow<Int> = _pointThemeSettingIndex

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
                when (_appThemeSetting.value) {
                    AppTheme.LIGHT_PROTOTYPE -> AppTheme.LIGHT_PROTOTYPE
                    AppTheme.DARK_PROTOTYPE -> AppTheme.DARK_PROTOTYPE
                },

                when (_pointThemeSettingIndex.value) {
                    PointThemeSetting.PROTOTYPE.index -> PointThemeSetting.PROTOTYPE
                    PointThemeSetting.COMING_SOON.index -> PointThemeSetting.COMING_SOON
                    else -> PointThemeSetting.PROTOTYPE
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
                dataStoreManager.getAppThemeSetting(),
                dataStoreManager.getPointThemeSetting(),
                dataStoreManager.getLanguageSetting(),
            ) { appTheme, pointTheme, language ->
                Triple(appTheme, pointTheme, language)
            }.collect { (appTheme, pointTheme, language) ->
                when (appTheme) {
                    AppTheme.LIGHT_PROTOTYPE -> {
                        _appThemeSetting.value = AppTheme.LIGHT_PROTOTYPE
                    }

                    AppTheme.DARK_PROTOTYPE -> {
                        _appThemeSetting.value = AppTheme.DARK_PROTOTYPE
                    }
                }

                when (pointTheme) {
                    PointThemeSetting.PROTOTYPE -> {
                        _pointThemeSettingIndex.value = PointThemeSetting.PROTOTYPE.index
                    }

                    PointThemeSetting.COMING_SOON -> {
                        _pointThemeSettingIndex.value = PointThemeSetting.COMING_SOON.index
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

    fun reverseAppThemeSetting(isDarkMode: Boolean) {
        _appThemeSetting.value =
            if (isDarkMode) AppTheme.DARK_PROTOTYPE
            else AppTheme.LIGHT_PROTOTYPE

    }

    fun updatePointThemeSettingIndex(index: Int) {
        _pointThemeSettingIndex.value = index
    }

    fun updateLanguageSettingIndex(index: Int) {
        _languageSettingIndex.value = index
    }
}