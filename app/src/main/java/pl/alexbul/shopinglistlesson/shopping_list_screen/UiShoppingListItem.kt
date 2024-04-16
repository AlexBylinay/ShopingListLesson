package pl.alexbul.shopinglistlesson.shopping_list_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import pl.alexbul.shopinglistlesson.ui.theme.DarkLight
import pl.alexbul.shopinglistlesson.ui.theme.DarkText
import  pl.alexbul.shopinglistlesson.R
import pl.alexbul.shopinglistlesson.data.ShoppingListItem
import pl.alexbul.shopinglistlesson.ui.theme.GreenLight
import pl.alexbul.shopinglistlesson.ui.theme.Red500
import pl.alexbul.shopinglistlesson.utils.Routs


@Composable
fun UiShoppingListItem(
    item: ShoppingListItem,
    onEvent: (ShoppingListEvent) -> Unit

) {
    ConstraintLayout(
        modifier = Modifier.padding(
            start = 4.dp, end = 6.dp, top = 18.dp
        )
    ) {
        val (card, deleteButton, editButton, counter) = createRefs()
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(card) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .clickable {
                  onEvent(ShoppingListEvent.OnItemClick(
                     Routs.ADD_ITEM + "/${item.id}"))

                })
        {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(
                    text = item.name,
                    style = TextStyle(
                        color = DarkText,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                )
                Text(
                    text = item.time,
                    style = TextStyle(
                        color = DarkLight,
                        fontSize = 11.sp
                    )
                )
                LinearProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp),
                    progress = 0.5f
                )

            }
        }
        IconButton(
            onClick = {
                onEvent(ShoppingListEvent.OnShowDeleteDialog(item))
            },
            modifier = Modifier
                .constrainAs(deleteButton) {
                    top.linkTo(card.top)
                    //bottom.linkTo(card.bottom)
                    end.linkTo(card.end)
                }
                .padding(end = 3.dp)
                .size(40.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.delete_icon),
                contentDescription = "Delete",
                modifier = Modifier
                    .clip(CircleShape)
                    .background(color = Red500)
                    .padding(2.dp),
                tint = DarkLight
            )

        }

        IconButton(
            onClick = {
                onEvent(ShoppingListEvent.OnShowDEventDialog(item))
            },
            modifier = Modifier
                .constrainAs(editButton) {
                    top.linkTo(card.top)
                    //bottom.linkTo(card.bottom)
                    end.linkTo(deleteButton.start)
                }
                .padding(end = 1.dp)
                .size(40.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.edit_icon),
                contentDescription = "Edit",
                modifier = Modifier
                    .clip(CircleShape)
                    .background(color = GreenLight)
                    .padding(2.dp),
                tint = DarkLight
            )

        }

        Card(
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier
                .constrainAs(counter) {
                    top.linkTo(card.top)
                    //bottom.linkTo(card.bottom)
                    end.linkTo(editButton.start)

                }
                .padding(end = 6.dp)
        ) {
            Text(
                text = "${item.allItemsCount}/${item.allSelectedItemsCount}",
                modifier = Modifier
                    .padding(
                        top = 8.dp,

                        end = 5.dp
                    )
                    .background(Red500)
            )
        }

    }
}