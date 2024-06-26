package pl.alexbul.shopinglistlesson.note_list_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarResult
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import pl.alexbul.shopinglistlesson.dialog.MainDialog
import pl.alexbul.shopinglistlesson.shopping_list_screen.UiShoppingListItem
import pl.alexbul.shopinglistlesson.ui.theme.GrayLight
import pl.alexbul.shopinglistlesson.ui.theme.TextLight
import pl.alexbul.shopinglistlesson.utils.UiEvent

@Composable
fun NoteListScreen(
    viewModel: NoteListViewModel = hiltViewModel(),
    onNavigate: (String) -> Unit
) {

    val scaffoldState = rememberScaffoldState()
   // val itemsList = viewModel.noteList.collectAsState(initial = emptyList())

    LaunchedEffect(key1 = true)
    {
        viewModel.uiEvent.collect { uiEvent ->
            when (uiEvent) {
                is UiEvent.Navigate -> {
                    onNavigate(uiEvent.route)
                }

                is UiEvent.ShowShackBar -> {
                    val resault = scaffoldState.snackbarHostState.showSnackbar(
                        message = uiEvent.message,
                        actionLabel = "Undone"
                    )
                    if (resault == SnackbarResult.ActionPerformed) {
                        viewModel.onEvent(NoteListEvent.UnDoneDeleteItem)
                    }
                }

                else -> {}
            }

        }
    }
    Scaffold(scaffoldState = scaffoldState, snackbarHost = {
        SnackbarHost(hostState = scaffoldState.snackbarHostState) { data ->
            Snackbar(
                snackbarData = data,
                backgroundColor = Color.Red,
                modifier = Modifier.padding(bottom = 100.dp)
            )
        }


    }) {

        Column(modifier = Modifier
            .fillMaxSize()
            .background(GrayLight)) {

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(7.dp),
                shape = RoundedCornerShape(16.dp)

            ) {
                TextField(value = viewModel.searchText, onValueChange = {text ->
                    viewModel.onEvent(NoteListEvent.OnTextSearchChange(text))
                }, label = {
                    Text(text = "Search...")
                },  colors = TextFieldDefaults
                    .textFieldColors(backgroundColor = Color.White)
                )

            }


            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                contentPadding = PaddingValues(bottom = 100.dp)
            )
            {
                items(viewModel.noteList) { item ->
                    UiNoteItem(viewModel.titleColor.value, item) { event ->
                        viewModel.onEvent(event)
                    }
                }
            }
            MainDialog(viewModel)
            if (viewModel.noteList.isEmpty()) {
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
}
