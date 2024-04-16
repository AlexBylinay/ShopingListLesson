package pl.alexbul.shopinglistlesson.main_screen

sealed class MainScreenEvent {
    object OnShowDEventDialog : MainScreenEvent()
    object OnItemSave : MainScreenEvent()
    data class Navigate(val route: String) : MainScreenEvent()
    data class NavigateMain(val route: String) : MainScreenEvent()

}