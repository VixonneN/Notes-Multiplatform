package com.khomichenko.notes.di

import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.khomichenko.add_note.di.AddNoteFeatureModule
import com.khomichenko.auth.di.AuthModule
import com.khomichenko.database.di.DatabaseCommonModule
import com.khomichenko.edit_note.di.EditNoteModule
import com.khomichenko.main.di.MainModule
import com.khomichenko.onboarding.di.OnboardingModule
import com.khomichenko.preferences.di.PreferencesModule
import com.khomichenko.registration.di.RegistrationModule
import com.khomichenko.root.di.RootModule
import com.khomichenko.settings.di.SettingsFeatureModule
import org.koin.dsl.module

val AppModule = module {
    includes(
        RootModule,
        AuthModule,
        MainModule,
        OnboardingModule,
        RegistrationModule,
        PreferencesModule,
        DatabaseCommonModule,
        NotesFeatureModule,
        AddNoteFeatureModule,
        EditNoteModule,
        SettingsFeatureModule
    )
    single<StoreFactory> { DefaultStoreFactory() }
}