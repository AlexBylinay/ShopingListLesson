package pl.alexbul.shopinglistlesson.note_list_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import pl.alexbul.shopinglistlesson.data.NoteItem
import pl.alexbul.shopinglistlesson.data.NoteRepository
import pl.alexbul.shopinglistlesson.datactore.DataStoreManager
import pl.alexbul.shopinglistlesson.dialog.DialogController
import pl.alexbul.shopinglistlesson.dialog.DialogEvent
import pl.alexbul.shopinglistlesson.shopping_list_screen.ShoppingListEvent
import pl.alexbul.shopinglistlesson.utils.UiEvent
import javax.inject.Inject


@HiltViewModel
class NoteListViewModel @Inject constructor(
    private val repository: NoteRepository,
    dataStoreManager: DataStoreManager
) : ViewModel(), DialogController {

    val noteListFlow = repository.getAllItems()
    private var noteItem: NoteItem? = null

    var originNoteList by mutableStateOf(listOf<NoteItem>())

    var noteList by mutableStateOf(listOf<NoteItem>())

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    var searchText by  mutableStateOf("")
        private set
    var titleColor = mutableStateOf("#9C300F")
    override var dialogTitle = mutableStateOf("Delete this note?")
        private set

    override var editableText = mutableStateOf("")
        private set

    override var openDialog = mutableStateOf(false)
        private set
    override var showEditText = mutableStateOf(false)
        private set


    init{
       viewModelScope.launch { dataStoreManager
           .getStringPreference(DataStoreManager.TITLE_COLOR,
               "#9C300F").collect{ color ->
                   titleColor.value = color

           }
       }
        viewModelScope.launch { noteListFlow.collect{
            list ->
            noteList = list
            originNoteList = list
        } }
    }

    private fun sendUiEvent(event1: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event1)
        }
    }

    fun onEvent(event: NoteListEvent){
        when (event){
            is NoteListEvent.OnShowDeleteDialog ->{
                openDialog.value = true
                noteItem = event.item
            }
            is NoteListEvent.OnItemClick -> {
                sendUiEvent(UiEvent.Navigate(event.route))
            }
            is NoteListEvent.UnDoneDeleteItem -> {
                viewModelScope.launch { repository.insertItem(noteItem!!) }
            }
            is NoteListEvent.OnTextSearchChange -> {
                searchText = event.text
                noteList = originNoteList.filter { note ->
                    note.title.lowercase().startsWith(searchText.lowercase())

                }
            }

        }

    }

    override fun onDialogEvent(event: DialogEvent) {
        when (event) {

            is DialogEvent.OnCancel -> {
                openDialog.value = false
            }

            is DialogEvent.OnConfirm -> {
                viewModelScope.launch{
                    repository.deleteItem(noteItem!!)
                    sendUiEvent(UiEvent.ShowShackBar("UnDone delete Item?"))
                }
                openDialog.value = false
            }
            else -> {}
        }


    }

}