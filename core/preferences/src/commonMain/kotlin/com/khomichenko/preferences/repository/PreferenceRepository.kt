package com.khomichenko.preferences.repository

import kotlinx.coroutines.flow.Flow

interface PreferenceRepository {
    fun getEndOnboarding() : Flow<Boolean>
    suspend fun setEndOnboarding(isEnd: Boolean)
    
    fun getUserToken() : Flow<String>
    suspend fun setUserToken(token: String)
}
