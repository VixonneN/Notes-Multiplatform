package com.khomichenko.preferences.repository

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.coroutines.FlowSettings
import com.russhwolf.settings.coroutines.toFlowSettings
import kotlinx.coroutines.flow.Flow
import platform.Foundation.NSUserDefaults

@OptIn(ExperimentalSettingsApi::class)
internal class PreferenceIosRepository : PreferenceRepository {
    
    private val settings: FlowSettings = NSUserDefaultsSettings(NSUserDefaults()).toFlowSettings()
    
    override fun getEndOnboarding(): Flow<Boolean> =
        settings.getBooleanFlow(ONBOARDING_KEY, false)

    override suspend fun setEndOnboarding(isEnd: Boolean) =
        settings.putBoolean(ONBOARDING_KEY, isEnd)
    
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
