package pl.alexbul.shopinglistlesson.note_list_screen

import pl.alexbul.shopinglistlesson.data.NoteItem

sealed  class NoteListEvent {
    data class OnShowDeleteDialog(val item: NoteItem): NoteListEvent()
    data class OnItemClick(val route: String): NoteListEvent()
    data class OnTextSearchChange(val text: String): NoteListEvent()
    object UnDoneDeleteItem: NoteListEvent()

}