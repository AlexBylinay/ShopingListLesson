package pl.alexbul.shopinglistlesson.note_list_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pl.alexbul.shopinglistlesson.data.NoteItem
import pl.alexbul.shopinglistlesson.ui.theme.TextLight
import pl.alexbul.shopinglistlesson.utils.Routs


@Composable
fun UiNoteItem(titleColor:String,
    item: NoteItem, onEvent:
    (NoteListEvent) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 5.dp,
                top = 5.dp,
                end = 5.dp
            )
            .clickable {
                onEvent(NoteListEvent.OnItemClick(
                  Routs.NEW_NOTE + "/${item.id}"
                ))
            }
    ) {

        Column(Modifier.fillMaxWidth()) {


            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 10.dp,
                        top = 5.dp,
                    )
            ) {
                Text(
                    modifier = Modifier
                        .padding(
                            start = 10.dp,
                            top = 6.dp
                        )
                        .weight(1f),
                    text = item.title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(android.graphics.Color.parseColor(titleColor))
                )
                Text(
                    modifier = Modifier.padding(
                        top = 6.dp,
                        end = 16.dp
                    ),
                    text = item.time,
                    fontSize = 14.sp,

                    )
            }
            Row(
                Modifier
                    .fillMaxWidth()

            ) {
                Text(
                    modifier = Modifier
                        .padding(
                            start = 10.dp,
                            top = 5.dp
                        )
                        .weight(1f),
                    text = item.description,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = TextLight
                )

                IconButton(
                    onClick = {
                     onEvent(NoteListEvent.OnShowDeleteDialog(item))
                    },

                    )
                {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "delete")
                }

            }

        }
    }

}