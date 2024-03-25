package pl.alexbul.shopinglistlesson.add_item_screen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import pl.alexbul.shopinglistlesson.data.AddItem
import pl.alexbul.shopinglistlesson.data.AddItemRepository
import pl.alexbul.shopinglistlesson.dialog.DialogController
import pl.alexbul.shopinglistlesson.dialog.DialogEvent
import pl.alexbul.shopinglistlesson.main_screen.MainScreenEvent

import javax.inject.Inject

@HiltViewModel
class AddItemViewModel @Inject constructor(
    private val repository: AddItemRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel(), DialogController {

    var itemsList: Flow<List<AddItem>>? = null
    var addItem: AddItem? = null
    var listId: Int = -1

    init {
        listId = savedStateHandle.get<String>("listId")?.toInt()!!
        itemsList = listId.let { repository.getAllItemsByID(it) }
        Log.d("MyLog", "List id View model $listId")
    }

    var itemText = mutableStateOf("")
        private set
    override var dialogTitle = mutableStateOf("Edit name:")
        private set

    override var editableText = mutableStateOf("")
        private set

    override var openDialog = mutableStateOf(false)
        private set
    override var showEditText = mutableStateOf(true)
        private set

    fun onEvent(event: AddItemEvent) {
        when (event) {
            is AddItemEvent.OnItemSave -> {
                viewModelScope.launch {
                    if (listId == -1) return@launch
                    repository.insertItem(
                        AddItem(
                            addItem?.id, itemText.value,
                            addItem?.isCheck ?: false,
                            listId
                        )
                    )
                    itemText.value = ""
                    addItem = null
                }
            }

            is AddItemEvent.OnShowDEventDialog -> {
                addItem = event.item
                openDialog.value = true
                editableText.value = addItem?.name ?: ""
            }

            is AddItemEvent.OnTextChange -> {
                itemText.value = event.text
            }

            is AddItemEvent.OnDelete -> {
                viewModelScope.launch { repository.deleteItem(event.item) }
            }

            is AddItemEvent.OnCheckedChange -> {
                viewModelScope.launch{repository.insertItem(event.item)}

            }
        }

    }

     override fun onDialogEvent(event: DialogEvent) {
        when (event) {

            is DialogEvent.OnCancel -> {
                openDialog.value = false
                editableText.value = ""
            }

            is DialogEvent.OnConfirm -> {
                openDialog.value = false
                itemText.value = editableText.value
                editableText.value = ""
            }

            is DialogEvent.OnTextChange -> {
                editableText.value = event.text
            }
        }

    }

    private fun updateShoppingListCount(){
        viewModelScope.launch {  }

    }
}