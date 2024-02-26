package pl.alexbul.shopinglistlesson.shopping_list_screen

import pl.alexbul.shopinglistlesson.data.ShoppingListItem

sealed class ShoppingListEvent{
    data class OnShowDeleteDialog(val item: ShoppingListItem):ShoppingListEvent()
    data class OnShowDEventDialog(val item: ShoppingListItem):ShoppingListEvent()
    data class OnItemClick (val route: String):ShoppingListEvent()
    object OnItemSave:ShoppingListEvent()
}
