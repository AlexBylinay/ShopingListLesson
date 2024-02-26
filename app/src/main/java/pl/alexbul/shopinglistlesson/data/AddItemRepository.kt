package pl.alexbul.shopinglistlesson.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

interface AddItemRepository {
    suspend fun insertItem(item:AddItem)
    suspend fun deleteItem(item:AddItem)
    fun getAllItemsByID (listId: Int): Flow<List<AddItem>>
    suspend fun getListItemsByID (listId: Int): ShoppingListItem
    suspend fun insertItem(item:ShoppingListItem)
}