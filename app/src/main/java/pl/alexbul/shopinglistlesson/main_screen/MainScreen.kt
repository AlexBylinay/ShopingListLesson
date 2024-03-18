package pl.alexbul.shopinglistlesson.main_screen

import android.annotation.SuppressLint
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import pl.alexbul.shopinglistlesson.R
import pl.alexbul.shopinglistlesson.dialog.MainDialog
import pl.alexbul.shopinglistlesson.navigation.NavigationGraph
import pl.alexbul.shopinglistlesson.settings_screen.SettingsScreen
import pl.alexbul.shopinglistlesson.shopping_list_screen.ShoppingListViewModel

@SuppressLint
@Composable
fun MainScreen(
    mainNavController: NavHostController,
    viewModel: MainScreenViewModel = hiltViewModel()) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNav(navController)
        },

        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.onEvent(MainScreenEvent.OnShowDEventDialog)
            })
            {
                Icon(
                    painter = painterResource(
                        id = R.drawable.add_icon
                    ),
                    contentDescription = "Add", tint = Color.White
                )

            }
        }, floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true
    ) {

        NavigationGraph(navController)
        MainDialog(viewModel)
    }

}