package com.khomichenko.onboarding.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.khomichenko.onboarding.store.OnboardingStore.Intent
import com.khomichenko.onboarding.store.OnboardingStoreFactory
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

internal class OnboardingComponentImpl(
    componentContext: ComponentContext,
    private val toAuth:() -> Unit,
    private val toMain:() -> Unit
) : KoinComponent, OnboardingComponent, ComponentContext by componentContext {
    
    private val store = instanceKeeper.getStore {
        OnboardingStoreFactory(
            storeFactory = get(),
            preferenceRepository = get(),
            moveToAuth = toAuth,
            moveToMain = toMain
        ).create()
    }
    
    override fun navigateToAuth() {
        store.accept(Intent.SetOnboardingComplete(isComplete = true))
    }
        
    
}