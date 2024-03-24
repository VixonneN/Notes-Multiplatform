package com.khomichenko.onboarding.di

import com.khomichenko.onboarding.component.OnboardingComponent
import com.khomichenko.onboarding.component.OnboardingComponentImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val OnboardingModule = module {
    factoryOf(::OnboardingComponentImpl) { bind<OnboardingComponent>() }
}