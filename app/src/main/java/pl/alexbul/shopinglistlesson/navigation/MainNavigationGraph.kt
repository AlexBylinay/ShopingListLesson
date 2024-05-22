package pl.alexbul.shopinglistlesson.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pl.alexbul.shopinglistlesson.about_screen.AboutScreen
import pl.alexbul.shopinglistlesson.add_item_screen.AddItemScreen
import pl.alexbul.shopinglistlesson.main_screen.MainScreen
import pl.alexbul.shopinglistlesson.new_note_screen.NewNoteScreen
import pl.alexbul.shopinglistlesson.note_list_screen.NoteListScreen
import pl.alexbul.shopinglistlesson.settings_screen.SettingsScreen
import pl.alexbul.shopinglistlesson.shopping_list_screen.ShoppingListScreen
import pl.alexbul.shopinglistlesson.utils.Routs

@Composable
fun MainNavigationGraph () {

val navController = rememberNavController()
    NavHost(navController = navController,
    startDestination = Routs.MAIN_SCREEN) {

        composable(Routs.ADD_ITEM+ "/{listId}") {
            AddItemScreen()
        }
        composable(Routs.NEW_NOTE+"/{noteId}" ) {
            NewNoteScreen(){
                navController.popBackStack()
            }
        }
        composable(Routs.MAIN_SCREEN) {
            MainScreen(navController)
        }


    }

}