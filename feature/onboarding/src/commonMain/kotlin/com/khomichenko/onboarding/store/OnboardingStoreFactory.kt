package com.khomichenko.onboarding.store

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.khomichenko.onboarding.store.OnboardingStore.*
import com.khomichenko.preferences.repository.PreferenceRepository
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch

internal class OnboardingStoreFactory(
    private val storeFactory: StoreFactory,
    private val preferenceRepository: PreferenceRepository,
    private val moveToAuth: () -> Unit,
    private val moveToMain: () -> Unit,
) {

    fun create(): OnboardingStore = object : OnboardingStore, Store<Intent, State, Result> by storeFactory.create(
        name = OnboardingStoreFactory::class.simpleName,
        initialState = State(),
        bootstrapper = SimpleBootstrapper(Unit),
        executorFactory = ::ExecutorImpl,
        reducer = ReducerImpl
    ) {}

    private inner class ExecutorImpl : CoroutineExecutor<Intent, Unit, State, Result, Nothing>() {
        override fun executeAction(action: Unit, getState: () -> State) {
            preferenceRepository.getEndOnboarding()
                .combine(preferenceRepository.getUserToken()) { isEnd: Boolean, token : String ->
                    when {
                        isEnd && token.isNotEmpty() -> moveToMain()
                        isEnd && token.isEmpty() -> moveToAuth()
                    }
                }
                .launchIn(scope)
        }

        override fun executeIntent(intent: Intent, getState: () -> State) {
            when (intent) {
                is Intent.SetOnboardingComplete -> setOnboardingState(isComplete = intent.isComplete)
            }
        }

        private fun setOnboardingState(isComplete: Boolean) {
            scope.launch {
                preferenceRepository.setEndOnboarding(isComplete)
                dispatch(Result.OnboardingChanged(isComplete = isComplete))
            }
        }
    }

    private object ReducerImpl : Reducer<State, Result> {
        override fun State.reduce(msg: Result) =
            when (msg) {
                is Result.OnboardingChanged -> copy(isOnboardingComplete = msg.isComplete)
            }
    }

}