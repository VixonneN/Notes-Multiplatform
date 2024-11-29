package com.khomichenko.root.store

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.khomichenko.preferences.repository.PreferenceRepository
import com.khomichenko.root.store.RootStore.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class RootStoreFactory(
    private val storeFactory: StoreFactory,
    private val preferenceRepository: PreferenceRepository
) {

    fun create() : RootStore =
        object : RootStore, Store<Intent, State, Label> by storeFactory.create(
            name = RootStore::class.qualifiedName,
            initialState = State(),
            bootstrapper = SimpleBootstrapper(Unit),
            executorFactory = ::ExecutorFactory,
            reducer = ReducerImpl
        ) {}

    private inner class ExecutorFactory: CoroutineExecutor<Intent, Unit, State, Msg, Label>() {

        override fun executeAction(action: Unit) {
            preferenceRepository.getEndOnboarding()
                .onEach { isCompleted ->
                    if (isCompleted) {
                        publish(Label.OnboardingCompleted)
                    }
                }.launchIn(scope)
        }

        override fun executeIntent(intent: Intent) {
            when(intent) {
                Intent.ChangeOnboardingCleared -> dispatch(Msg.OnboardingChanged)
            }
        }
    }

    private object ReducerImpl: Reducer<State, Msg> {
        override fun State.reduce(msg: Msg): State =
            when(msg) {
                Msg.OnboardingChanged -> copy(isOnboardingCleared = true)
            }

    }
}