package com.khomichenko.registration.component

import com.arkivanov.decompose.ComponentContext
import org.koin.core.component.KoinComponent

internal class RegistrationComponentImpl(
    componentContext: ComponentContext
) : RegistrationComponent, KoinComponent, ComponentContext by componentContext {

}