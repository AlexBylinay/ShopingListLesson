package pl.alexbul.shopinglistlesson.dialog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pl.alexbul.shopinglistlesson.ui.theme.DarkLight
import pl.alexbul.shopinglistlesson.ui.theme.DarkText
import pl.alexbul.shopinglistlesson.ui.theme.GrayLight
import pl.alexbul.shopinglistlesson.ui.theme.GrayLight2


@Composable
fun MainDialog(dialogController: DialogController) {
    if (dialogController.openDialog.value){
       AlertDialog(
           onDismissRequest = {
           dialogController.onDialogEvent(DialogEvent.OnCancel)
           },
          title = null,
           text = {
               Column(modifier = Modifier.fillMaxWidth()) {
                   Text(text = dialogController.dialogTitle.value,
                   style = TextStyle(
                       color = DarkLight,
                       fontWeight = FontWeight.Bold,
                       fontSize = 20.sp
                   )
                   )
                   Spacer(modifier = Modifier.height(10.dp))
                   if (dialogController.showEditText.value)
                       TextField(
                           value = dialogController.editableText.value,
                           onValueChange = { text ->
                               dialogController.onDialogEvent(DialogEvent.OnTextChange(text))
                           },
                           label = { Text(text = "List nme") },
                           colors = TextFieldDefaults.textFieldColors(
                               backgroundColor = GrayLight2,
                               focusedIndicatorColor = Color.Transparent,
                               unfocusedIndicatorColor = Color.Transparent
                           ),
                           shape = RoundedCornerShape(8.dp),
                           singleLine = true,
                           textStyle = TextStyle(
                               color = DarkText,
                               fontSize = 16.sp
                           )

                           )

               }
           },
 confirmButton = {
     TextButton(onClick = {
         dialogController.onDialogEvent(DialogEvent.OnConfirm)
     }) {
         Text(text = "Ok" )

     }

 },
           dismissButton  = {
               TextButton(onClick = {
                   dialogController.onDialogEvent(DialogEvent.OnCancel)
               }) {
                   Text(text = "Cancel" )

               }

           }


       )
    }

}