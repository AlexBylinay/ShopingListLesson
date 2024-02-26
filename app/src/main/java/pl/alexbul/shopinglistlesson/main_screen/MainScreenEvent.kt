package pl.alexbul.shopinglistlesson.main_screen

sealed class MainScreenEvent{
    object OnShowDEventDialog: MainScreenEvent()
    object OnItemSave: MainScreenEvent()
}

