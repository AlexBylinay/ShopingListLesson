package pl.alexbul.shopinglistlesson.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import pl.alexbul.shopinglistlesson.about_screen.AboutScreen
import pl.alexbul.shopinglistlesson.note_list_screen.NoteListScreen
import pl.alexbul.shopinglistlesson.settings_screen.SettingsScreen
import pl.alexbul.shopinglistlesson.shopping_list_screen.ShoppingListScreen
import pl.alexbul.shopinglistlesson.utils.Routs
import pl.alexbul.shopinglistlesson.utils.UiEvent

@Composable
fun NavigationGraph (navController: NavHostController, onNavigate: (String) -> Unit){

    NavHost(navController = navController,
        startDestination = Routs.SHOPPING_LIST){
        composable(Routs.SHOPPING_LIST){
            ShoppingListScreen(){route ->
                onNavigate(route)
            }
        }
        composable(Routs.NOTE_LIST){
           NoteListScreen()
        }
        composable(Routs.ABOUT){
            AboutScreen()
        }
        composable(Routs.SETTINGS){
            SettingsScreen()
        }

    }
}