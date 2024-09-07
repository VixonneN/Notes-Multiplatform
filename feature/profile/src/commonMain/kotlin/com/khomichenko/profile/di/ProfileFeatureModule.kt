package com.khomichenko.profile.di

import com.khomichenko.profile.component.ProfileRootComponent
import com.khomichenko.profile.component.ProfileRootRootComponentImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val ProfileFeatureModule = module {
    factoryOf(::ProfileRootRootComponentImpl) { bind<ProfileRootComponent>() }
}