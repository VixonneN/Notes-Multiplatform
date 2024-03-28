package com.khomichenko.settings.component

import com.arkivanov.decompose.ComponentContext
import org.koin.core.component.KoinComponent

internal class SettingsComponentImpl(
    componentContext: ComponentContext,
    private val closeSlotComponent:() -> Unit
) : SettingsComponent, KoinComponent, ComponentContext by componentContext {
    override fun closeSettingsSlot() =
        closeSlotComponent()



}