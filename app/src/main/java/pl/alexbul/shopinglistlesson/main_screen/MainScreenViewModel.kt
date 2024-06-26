package pl.alexbul.shopinglistlesson.main_screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import pl.alexbul.shopinglistlesson.data.ShoppingListItem
import pl.alexbul.shopinglistlesson.data.ShoppingListRepository
import pl.alexbul.shopinglistlesson.dialog.DialogController
import pl.alexbul.shopinglistlesson.dialog.DialogEvent
import pl.alexbul.shopinglistlesson.shopping_list_screen.ShoppingListEvent
import pl.alexbul.shopinglistlesson.utils.Routs
import pl.alexbul.shopinglistlesson.utils.UiEvent
import pl.alexbul.shopinglistlesson.utils.getCurrentTime
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel
@Inject constructor(
    private val repository: ShoppingListRepository
) : ViewModel(), DialogController {
    private val _uiEvent = Channel<UiEvent>()

    val uiEvent = _uiEvent.receiveAsFlow()

    override var dialogTitle = mutableStateOf("List name")
        private set

    override var editableText = mutableStateOf("")
        private set

    override var openDialog = mutableStateOf(false)
        private set
    override var showEditText = mutableStateOf(true)
        private set

    var showFloatingButton = mutableStateOf(true)
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
                            getCurrentTime(),
                            0,
                            0
                        )
                    )
                }
            }

            is MainScreenEvent.OnNewItemClick -> {
                if (event.route == Routs.SHOPPING_LIST) {
                    openDialog.value = true
                } else {
                    sendUiEvent(UiEvent.NavigateMain(Routs.NEW_NOTE + "/-1"))
                }
            }

            is MainScreenEvent.Navigate -> {
                sendUiEvent(UiEvent.Navigate(event.route))

                showFloatingButton.value = !(event.route == Routs.ABOUT ||
                        event.route == Routs.SETTINGS)
            }

            is MainScreenEvent.NavigateMain -> {
                sendUiEvent(UiEvent.NavigateMain(event.route))
            }

            else -> {}
        }
    }


    override fun onDialogEvent(event: DialogEvent) {
        when (event) {

            is DialogEvent.OnCancel -> {
                openDialog.value = false
                editableText.value = ""
            }

            is DialogEvent.OnConfirm -> {
                (showEditText.value)
                onEvent(MainScreenEvent.OnItemSave)

                openDialog.value = false
                editableText.value = ""
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