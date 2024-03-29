package com.khomichenko.main.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.slot.SlotNavigation
import com.arkivanov.decompose.router.slot.activate
import com.arkivanov.decompose.router.slot.childSlot
import com.arkivanov.decompose.router.slot.dismiss
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import com.khomichenko.add_note.component.AddNoteComponent
import com.khomichenko.edit_note.component.EditNoteComponent
import com.khomichenko.favorites.component.FavoritesComponent
import com.khomichenko.main.component.MainComponent.*
import com.khomichenko.notes.component.NotesComponent
import com.khomichenko.settings.component.SettingsComponent
import kotlinx.serialization.Serializable
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.parameter.parametersOf

internal class MainComponentImpl(
    componentContext: ComponentContext
) : MainComponent, KoinComponent, ComponentContext by componentContext {

    private fun listNotes(componentContext: ComponentContext) = get<NotesComponent>(
        parameters = {
            parametersOf(
                componentContext,
                ::openAddNoteSlot,
                ::openEditNoteSlot
            )
        }
    )

    private fun favoritesNotes(componentContext: ComponentContext) = get<FavoritesComponent>(
        parameters = {
            parametersOf(
                componentContext,
                ::openAddNoteSlot,
                ::openEditNoteSlot
            )
        }
    )

    private fun addNote(componentContext: ComponentContext) = get<AddNoteComponent>(
        parameters = {
            parametersOf(
                componentContext,
                ::dismissSlotChild
            )
        }
    )

    private fun editNote(componentContext: ComponentContext, idNote: Int) = get<EditNoteComponent>(
        parameters = {
            parametersOf(
                componentContext,
                idNote,
                ::dismissSlotChild
            )
        }
    )

    private fun settings(componentContext: ComponentContext) = get<SettingsComponent>(
        parameters = {
            parametersOf(
                componentContext,
                ::dismissSlotChild
            )
        }
    )

    private val stackNavigation = StackNavigation<StackConfig>()
    private val slotNavigation = SlotNavigation<SlotConfig>()

    private val _stack = childStack(
        source = stackNavigation,
        initialConfiguration = StackConfig.ListNotes,
        serializer = StackConfig.serializer()
    ) { configuration, componentContext ->
        when (configuration) {
            StackConfig.ListNotes -> ChildBottomNavigation.ListNotes(listNotes(componentContext))
            StackConfig.Favorites -> ChildBottomNavigation.FavoritesNotes(favoritesNotes(componentContext))
            StackConfig.Profile -> TODO()
        }
    }

    override val stack: Value<ChildStack<*, ChildBottomNavigation>>
        get() = _stack

    override val activeChildIndex: Value<Int>
        get() = stack.map { it.active.instance.index }

    private val _slot = childSlot(
        source = slotNavigation,
        serializer = SlotConfig.serializer(),
        handleBackButton = false
    ) { configuration, componentContext ->
        when (configuration) {
            SlotConfig.AddNote -> SlotChild.AddNote(addNote(componentContext))

            SlotConfig.Settings -> SlotChild.Settings(settings(componentContext))

            is SlotConfig.EditNote -> SlotChild.ShowNote(
                editNote(
                    componentContext,
                    configuration.id
                )
            )
        }
    }

    override val slot: Value<ChildSlot<*, SlotChild>>
        get() = _slot

    override fun onShelfSelect(index: Int) {
        stackNavigation.bringToFront(
            when (index) {
                1 -> StackConfig.Favorites
                2 -> StackConfig.Profile
                else -> StackConfig.ListNotes
            }
        )
    }

    override fun dismissSlotChild() {
        slotNavigation.dismiss()
    }

    override fun openAddNoteSlot() {
        slotNavigation.activate(SlotConfig.AddNote)
    }

    override fun openEditNoteSlot(id: Int) {
        slotNavigation.activate(SlotConfig.EditNote(id))
    }

    override fun openSettingsSlot() {
        slotNavigation.activate(SlotConfig.Settings)
    }

    @Serializable
    sealed interface StackConfig {
        @Serializable
        data object ListNotes : StackConfig

        @Serializable
        data object Favorites : StackConfig

        @Serializable
        data object Profile : StackConfig
    }

    @Serializable
    sealed interface SlotConfig {
        @Serializable
        data object AddNote : SlotConfig

        @Serializable
        data object Settings : SlotConfig

        @Serializable
        data class EditNote(val id: Int) : SlotConfig
    }
}
