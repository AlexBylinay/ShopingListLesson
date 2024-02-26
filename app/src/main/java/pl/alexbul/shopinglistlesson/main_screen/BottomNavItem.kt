package pl.alexbul.shopinglistlesson.main_screen

import pl.alexbul.shopinglistlesson.R
import pl.alexbul.shopinglistlesson.utils.Routs

sealed class BottomNavItem(val title:String, val iconId: Int, val route:String){
   object ListItem:BottomNavItem("List", R.drawable.list_icon, Routs.SHOPPING_LIST)
    object NoteItem:BottomNavItem("Note", R.drawable.note_icon,Routs.NOTE_LIST)
    object AboutItem:BottomNavItem("About", R.drawable.about_icon,Routs.ABOUT)
    object SettingItem:BottomNavItem("Setting", R.drawable.settings_icon,Routs.SETTINGS)
}

