package pl.alexbul.shopinglistlesson.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "shop_list_name")
data class ShoppingListItem(
    @PrimaryKey
    val id: Int? =0,
    val name: String,
    val time: String,
    val allItemsCount: Int,
    val allSelectedItemsCount: Int,
)