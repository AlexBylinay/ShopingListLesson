package pl.alexbul.shopinglistlesson.add_item_screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AddItemScreen() {
    Text(text = "Add Item Screen",
        modifier = Modifier. fillMaxSize()
            .wrapContentWidth()
            .wrapContentHeight())
}