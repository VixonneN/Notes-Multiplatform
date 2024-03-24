package com.khomichenko.preferences.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.ExperimentalSettingsImplementation
import com.russhwolf.settings.datastore.DataStoreSettings
import kotlinx.coroutines.flow.Flow

@OptIn(ExperimentalSettingsImplementation::class, ExperimentalSettingsApi::class)
internal class PreferenceAndroidRepository(
    context: Context
) : PreferenceRepository {
    
    private val Context.datastore: DataStore<Preferences> by preferencesDataStore("prefs")

    @OptIn(ExperimentalSettingsApi::class)
    private val settings = DataStoreSettings(datastore = context.datastore)
    
    
    override fun getEndOnboarding(): Flow<Boolean> =
        settings.getBooleanFlow(ONBOARDING_KEY, false)

    override suspend fun setEndOnboarding(isEnd: Boolean) {
        settings.putBoolean(ONBOARDING_KEY, isEnd)
    }

    override fun getUserToken(): Flow<String> =
        settings.getStringFlow(TOKEN_KEY, "")

    override suspend fun setUserToken(token: String) {
        settings.putString(TOKEN_KEY, token)
    }

    private companion object {
        const val ONBOARDING_KEY = "onboarding"
        const val TOKEN_KEY = "token"
    }

}