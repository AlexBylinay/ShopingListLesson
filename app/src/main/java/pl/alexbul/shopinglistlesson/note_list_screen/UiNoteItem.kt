package pl.alexbul.shopinglistlesson.note_list_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun UiNoteItem(
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 5.dp,
                top = 5.dp,
                end = 5.dp
            )
            .clickable { }
    ) {
Row (Modifier
    .fillMaxWidth()) {
    Text(text = "Note 1")

}
    }

}