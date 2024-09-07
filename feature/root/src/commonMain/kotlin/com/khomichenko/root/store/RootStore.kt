package com.khomichenko.root.store

import com.arkivanov.mvikotlin.core.store.Store
import com.khomichenko.root.store.RootStore.*

interface RootStore: Store<Intent, State, Label> {

    data class State(
        val isOnboardingCleared: Boolean = false
    )

    sealed interface Intent {
        data object ChangeOnboardingCleared: Intent
    }

    sealed interface Msg {
        data object OnboardingChanged: Msg
    }

    sealed class Label {
        data object OnboardingCompleted: Label()
    }
}