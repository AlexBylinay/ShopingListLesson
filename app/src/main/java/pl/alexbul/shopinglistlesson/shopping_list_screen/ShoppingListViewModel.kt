package pl.alexbul.shopinglistlesson.shopping_list_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import pl.alexbul.shopinglistlesson.data.ShoppingListItem
import pl.alexbul.shopinglistlesson.data.ShoppingListRepository
import pl.alexbul.shopinglistlesson.dialog.DialogEvent
import pl.alexbul.shopinglistlesson.dialog.DialogController
import pl.alexbul.shopinglistlesson.utils.UiEvent
import pl.alexbul.shopinglistlesson.utils.getCurrentTime
import javax.inject.Inject

@HiltViewModel
class ShoppingListViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: ShoppingListRepository
) : ViewModel(), DialogController {

    private val _uiEvent = Channel<UiEvent>()

    val uiEvent = _uiEvent.receiveAsFlow()

     val list = repository.getAllItems()

    private var listItem: ShoppingListItem? = null

    override var dialogTitle = mutableStateOf("")
        private set

    override var editableText = mutableStateOf("")
        private set

    override var openDialog = mutableStateOf(false)
        private set
    override var showEditText = mutableStateOf(false)
        private set

    fun onEvent(event: ShoppingListEvent) {
        when (event) {
            is ShoppingListEvent.OnItemSave -> {
                if (editableText.value.isEmpty()) return
                viewModelScope.launch {
                    repository.insertItem(
                        ShoppingListItem(
                            listItem?.id, editableText.value,
                            listItem?.time ?: getCurrentTime(),
                            listItem?.allItemsCount ?: 0,
                            listItem?.allSelectedItemsCount ?: 0
                        )
                    )
                }
            }
            is ShoppingListEvent.OnItemClick -> {
                sendUiEvent(UiEvent.Navigate(event.route))
            }
            is ShoppingListEvent.OnShowDEventDialog -> {
                listItem = event.item
                openDialog.value = true
                editableText.value = listItem?.name ?: ""
                dialogTitle.value = "List Name:"
                showEditText.value = true


            }
            is ShoppingListEvent.OnShowDeleteDialog -> {
                listItem = event.item
                openDialog.value = true
                dialogTitle.value = "Delete this item?"
                showEditText.value = false
            }
        }
    }

    override fun onDialogEvent(event: DialogEvent) {
        when (event) {

            is DialogEvent.OnCancel -> {
                openDialog.value = false
            }
            is DialogEvent.OnConfirm -> {
                if (showEditText.value) {
                    onEvent(ShoppingListEvent.OnItemSave)
                } else {
                    viewModelScope.launch {
                        listItem?.let { repository.deleteItem(it) }
                    }
                }
                openDialog.value = false


            }
            is DialogEvent.OnTextChange -> {
                editableText.value = event.text
            }


        }
    }

    private fun sendUiEvent(event1: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event1)
        }
    }

}




