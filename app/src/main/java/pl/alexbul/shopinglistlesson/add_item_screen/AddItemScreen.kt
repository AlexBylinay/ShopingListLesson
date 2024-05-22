package pl.alexbul.shopinglistlesson.add_item_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import pl.alexbul.shopinglistlesson.R
import pl.alexbul.shopinglistlesson.dialog.MainDialog
import pl.alexbul.shopinglistlesson.main_screen.MainScreenViewModel
import pl.alexbul.shopinglistlesson.shopping_list_screen.ShoppingListViewModel
import pl.alexbul.shopinglistlesson.ui.theme.BlueLight
import pl.alexbul.shopinglistlesson.ui.theme.DarkLight
import pl.alexbul.shopinglistlesson.ui.theme.DarkText
import pl.alexbul.shopinglistlesson.ui.theme.GrayLight
import pl.alexbul.shopinglistlesson.ui.theme.TextLight
import pl.alexbul.shopinglistlesson.utils.UiEvent

@Composable
fun AddItemScreen(
    viewModel: AddItemViewModel = hiltViewModel()
) {

    val scaffoldState = rememberScaffoldState()
    val itemsList = viewModel.itemsList?.collectAsState(initial = emptyList())

    LaunchedEffect(key1 = true )
    {
        viewModel.uiEvent.collect{uiEvent ->
            when (uiEvent){
                is UiEvent.ShowShackBar ->{
                   scaffoldState.snackbarHostState.showSnackbar(uiEvent.message)
                }
                else ->{}
            }

        }
    }

    Scaffold(scaffoldState = scaffoldState) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(GrayLight)
        ) {


            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Row(
                    Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
                )
                {

                    TextField(
                        modifier = Modifier.weight(1f),
                        value = viewModel.itemText.value,
                        onValueChange = { text -> viewModel.onEvent(AddItemEvent.OnTextChange(text)) },
                        label = { Text(text = "New Item", fontSize = 12.sp) },
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.White,
                            focusedIndicatorColor = BlueLight,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        textStyle = TextStyle(
                            fontSize = 16.sp,
                            color = DarkText
                        ),
                        singleLine = true
                    )
                    IconButton(onClick = {
                        viewModel.onEvent(AddItemEvent.OnItemSave)
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.add_icon),
                            contentDescription =
                            "add item"
                        )

                    }

                }
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = 8.dp, start = 8.dp, end = 8.dp
                    )
            ) {
                if (itemsList?.value != null) {
                    items(itemsList.value) { item ->
                        UiAddItem(item = item, onEvent = { event ->
                            viewModel.onEvent(event)
                        })
                    }
                }
            }
        }
        MainDialog(viewModel)
        if (itemsList?.value?.isEmpty() == true) {
            Text(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentHeight(),
                text = "EMPTY",
                fontSize = 30.sp,
                color = TextLight,
                textAlign = TextAlign.Center
            )
        }
    }
}