package pl.alexbul.shopinglistlesson.main_screen

import android.annotation.SuppressLint
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.flow.collect
import pl.alexbul.shopinglistlesson.R
import pl.alexbul.shopinglistlesson.dialog.MainDialog
import pl.alexbul.shopinglistlesson.navigation.NavigationGraph
import pl.alexbul.shopinglistlesson.settings_screen.SettingsScreen
import pl.alexbul.shopinglistlesson.shopping_list_screen.ShoppingListViewModel
import pl.alexbul.shopinglistlesson.utils.Routs
import pl.alexbul.shopinglistlesson.utils.UiEvent

@SuppressLint
@Composable
fun MainScreen(
    mainNavController: NavHostController,
    viewModel: MainScreenViewModel = hiltViewModel()
) {
    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { uiEvent ->
            when (uiEvent) {
                is UiEvent.NavigateMain -> {
                    mainNavController.navigate(uiEvent.route)
                }

                is UiEvent.Navigate -> {
                    navController.navigate(uiEvent.route)

                }

                else -> {}
            }
        }
    }

    Scaffold(
        bottomBar = {
            BottomNav(currentRoute) { route ->
                viewModel.onEvent(MainScreenEvent.Navigate(route))
            }
        },

        floatingActionButton = {
            if (viewModel.showFloatingButton.value)
            FloatingActionButton(onClick = {
                viewModel.onEvent(MainScreenEvent.OnNewItemClick(currentRoute?: Routs.SHOPPING_LIST))
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

        NavigationGraph(navController) { route ->
            viewModel.onEvent(MainScreenEvent.NavigateMain(route))
        }
        MainDialog(viewModel)
    }

}