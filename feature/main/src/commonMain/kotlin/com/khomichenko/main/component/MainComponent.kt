package com.khomichenko.main.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.khomichenko.add_note.component.AddNoteComponent
import com.khomichenko.edit_note.component.EditNoteComponent
import com.khomichenko.favorites.component.FavoritesComponent
import com.khomichenko.notes.component.NotesComponent
import com.khomichenko.settings.component.SettingsComponent

interface MainComponent {

    //todo
    sealed class ChildBottomNavigation(val index: Int) {
        data class ListNotes(val component: NotesComponent) : ChildBottomNavigation(index = 0)
        data class FavoritesNotes(val component: FavoritesComponent) : ChildBottomNavigation(index = 1)
        data class Profile(val component: ComponentContext) : ChildBottomNavigation(index = 2)
    }

    sealed interface SlotChild {
        data class AddNote(val component: AddNoteComponent) : SlotChild
        data class Settings(val component: SettingsComponent) : SlotChild
        data class ShowNote(val component: EditNoteComponent) : SlotChild
    }

    val stack: Value<ChildStack<*, ChildBottomNavigation>>

    val activeChildIndex: Value<Int>

    val slot: Value<ChildSlot<*, SlotChild>>

    fun onShelfSelect(index: Int)
    fun dismissSlotChild()

    fun openAddNoteSlot()
    fun openEditNoteSlot(id: Int)

    fun openSettingsSlot()
}