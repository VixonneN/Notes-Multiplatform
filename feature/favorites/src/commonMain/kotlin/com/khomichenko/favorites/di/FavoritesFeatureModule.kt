package com.khomichenko.favorites.di

import com.khomichenko.favorites.component.FavoritesComponent
import com.khomichenko.favorites.component.FavoritesComponentImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val FavoritesFeatureModule = module {
    factoryOf(::FavoritesComponentImpl) { bind<FavoritesComponent>() }
}