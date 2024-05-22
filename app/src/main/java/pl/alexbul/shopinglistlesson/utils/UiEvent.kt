package pl.alexbul.shopinglistlesson.utils

import android.os.Message

sealed class UiEvent{
    object PopBackStack: UiEvent()
    data class Navigate(val route: String): UiEvent()
    data class NavigateMain(val route: String): UiEvent()
    data class ShowShackBar(val message: String): UiEvent()




}
