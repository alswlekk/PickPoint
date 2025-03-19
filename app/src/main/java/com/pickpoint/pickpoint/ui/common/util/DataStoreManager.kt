package com.pickpoint.pickpoint.ui.common.util

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.pickpoint.pickpoint.ui.model.setting.LanguageSetting
import com.pickpoint.pickpoint.ui.model.setting.PointThemeSetting
import com.pickpoint.pickpoint.ui.theme.AppTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

private val Context.dataStore by preferencesDataStore(name = "dataStore")

class DataStoreManager(private val context: Context) {

    private val appThemeKey = stringPreferencesKey("app_theme")
    private val pointThemeKey = stringPreferencesKey("point_theme")
    private val languageKey = stringPreferencesKey("language")

    suspend fun saveAllSettings(
        appThemeSetting: AppTheme,
        pointThemeSetting: PointThemeSetting,
        languageSetting: LanguageSetting,
    ) {
        context.dataStore.edit { preferences ->
            preferences[appThemeKey] = when (appThemeSetting) {
                AppTheme.LIGHT_PROTOTYPE -> AppTheme.LIGHT_PROTOTYPE.name
                AppTheme.DARK_PROTOTYPE -> AppTheme.DARK_PROTOTYPE.name
            }
            preferences[pointThemeKey] = when (pointThemeSetting) {
                PointThemeSetting.PROTOTYPE -> PointThemeSetting.PROTOTYPE.value
                PointThemeSetting.COMING_SOON -> PointThemeSetting.COMING_SOON.value
            }
            preferences[languageKey] = when (languageSetting) {
                LanguageSetting.KOREAN -> LanguageSetting.KOREAN.value
                LanguageSetting.ENGLISH -> LanguageSetting.ENGLISH.value
                LanguageSetting.JAPANESE -> LanguageSetting.JAPANESE.value
            }
        }
    }

    fun getAppThemeSetting(): Flow<AppTheme> {
        return context.dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                when (preferences[appThemeKey]) {
                    AppTheme.LIGHT_PROTOTYPE.name -> AppTheme.LIGHT_PROTOTYPE
                    AppTheme.DARK_PROTOTYPE.name -> AppTheme.DARK_PROTOTYPE
                    else -> AppTheme.LIGHT_PROTOTYPE
                }
            }
    }

    fun getPointThemeSetting(): Flow<PointThemeSetting> {

        return context.dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                when (preferences[pointThemeKey]) {
                    PointThemeSetting.PROTOTYPE.value -> PointThemeSetting.PROTOTYPE
                    PointThemeSetting.COMING_SOON.value -> PointThemeSetting.COMING_SOON
                    else -> PointThemeSetting.PROTOTYPE
                }
            }
    }

    fun getLanguageSetting(): Flow<LanguageSetting> {
        return context.dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                when (preferences[languageKey]) {
                    LanguageSetting.KOREAN.value -> LanguageSetting.KOREAN
                    LanguageSetting.ENGLISH.value -> LanguageSetting.ENGLISH
                    LanguageSetting.JAPANESE.value -> LanguageSetting.JAPANESE
                    else -> LanguageSetting.KOREAN
                }
            }
    }
}