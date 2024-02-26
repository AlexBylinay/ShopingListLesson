package pl.alexbul.shopinglistlesson.dialog

import androidx.compose.runtime.MutableState

interface DialogController {
   val dialogTitle: MutableState<String>
    val editableText: MutableState<String>
    val openDialog: MutableState<Boolean>
    val showEditText: MutableState<Boolean>
    fun onDialogEvent(event: DialogEvent)

}