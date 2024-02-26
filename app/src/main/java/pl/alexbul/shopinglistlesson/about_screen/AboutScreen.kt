package pl.alexbul.shopinglistlesson.about_screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AboutScreen () {
    Text(text = "About Screen",
        modifier = Modifier. fillMaxSize()
            .wrapContentWidth()
            .wrapContentHeight())
}
