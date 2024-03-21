package pl.alexbul.shopinglistlesson.add_item_screen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import pl.alexbul.shopinglistlesson.data.AddItem
import pl.alexbul.shopinglistlesson.data.AddItemRepository
import pl.alexbul.shopinglistlesson.dialog.DialogController
import pl.alexbul.shopinglistlesson.dialog.DialogEvent
import pl.alexbul.shopinglistlesson.main_screen.MainScreenEvent

import javax.inject.Inject

@HiltViewModel
class AddItemViewModel @Inject constructor(
    private val repository: AddItemRepository,
    savedStateHandle: SavedStateHandle)
    : ViewModel(), DialogController {

        var itemList: Flow<List<AddItem>>?=null
     init{
       val listId = savedStateHandle.get<String>("listId")?.toInt()
         itemList = listId?.let { repository.getAllItemsByID(it) }
         Log.d("MyLog", "List id View model $listId")
     }

    override var dialogTitle = mutableStateOf("")
        private set

    override var editableText = mutableStateOf("")
        private set

    override var openDialog = mutableStateOf(false)
        private set
    override var showEditText = mutableStateOf(true)
        private set

    override fun onDialogEvent(event: DialogEvent) {
        when (event) {

            is DialogEvent.OnCancel -> {
                openDialog.value = false
                editableText.value=""
            }
            is DialogEvent.OnConfirm -> {
                (showEditText.value)
             //   onEvent(MainScreenEvent.OnItemSave)

                openDialog.value = false
                editableText.value=""
            }
            is DialogEvent.OnTextChange -> {
                editableText.value = event.text
            }
        }

    }
}