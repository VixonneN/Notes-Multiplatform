package com.khomichenko.onboarding.store

import com.arkivanov.mvikotlin.core.store.Store
import com.khomichenko.onboarding.store.OnboardingStore.*

internal interface OnboardingStore: Store<Intent, State, Result> {
    
    sealed interface Intent {
        data class SetOnboardingComplete(val isComplete: Boolean): Intent
    }
    
    sealed interface Result {
        data class OnboardingChanged(val isComplete: Boolean): Result
    }
    
    data class State(
        val isOnboardingComplete: Boolean = false
    )
}
