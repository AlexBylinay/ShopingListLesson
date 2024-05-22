package pl.alexbul.shopinglistlesson.add_item_screen

import pl.alexbul.shopinglistlesson.data.AddItem
import pl.alexbul.shopinglistlesson.shopping_list_screen.ShoppingListEvent

sealed class AddItemEvent {
    data class OnDelete(val item: AddItem): AddItemEvent()
    data class OnShowDEventDialog(val item: AddItem): AddItemEvent()
    data class OnTextChange(val text: String): AddItemEvent()
    data class OnCheckedChange(val item: AddItem): AddItemEvent()
    object OnItemSave: AddItemEvent()
    
}