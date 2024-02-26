package pl.alexbul.shopinglistlesson.main_screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import pl.alexbul.shopinglistlesson.data.ShoppingListItem
import pl.alexbul.shopinglistlesson.data.ShoppingListRepository
import pl.alexbul.shopinglistlesson.dialog.DialogController
import pl.alexbul.shopinglistlesson.dialog.DialogEvent
import pl.alexbul.shopinglistlesson.shopping_list_screen.ShoppingListEvent
import pl.alexbul.shopinglistlesson.utils.UiEvent
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel
@Inject constructor(
    private val repository: ShoppingListRepository
) : ViewModel(), DialogController
{

    override var dialogTitle = mutableStateOf("List name")
        private set

    override var editableText = mutableStateOf("")
        private set

    override var openDialog = mutableStateOf(false)
        private set
    override var showEditText = mutableStateOf(true)
        private set


    fun onEvent(event: MainScreenEvent) {
        when (event) {
            is MainScreenEvent.OnItemSave -> {
                if (editableText.value.isEmpty()) return
                viewModelScope.launch {
                    repository.insertItem(
                        ShoppingListItem(
                            null,
                            editableText.value,
                            "02.10.2023 14:47",
                             0,
                             0
                        )
                    )
                }
            }

            is MainScreenEvent.OnShowDEventDialog -> {
                openDialog.value = true
            }

        }
    }




    override fun onDialogEvent(event: DialogEvent) {
        when (event) {

            is DialogEvent.OnCancel -> {
                openDialog.value = false
                editableText.value=""
            }
            is DialogEvent.OnConfirm -> {
               (showEditText.value)
                    onEvent(MainScreenEvent.OnItemSave)

                openDialog.value = false
                editableText.value=""
            }
            is DialogEvent.OnTextChange -> {
                editableText.value = event.text
            }
        }

    }
}