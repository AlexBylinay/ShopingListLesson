package pl.alexbul.shopinglistlesson.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface AddItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item:AddItem)
    @Delete
    suspend fun deleteItem(item:AddItem)
    @Query("SELECT * FROM add_item WHERE listId = :listId" )
    fun getAllItemsByID (listId: Int): Flow<List<AddItem>>

    @Query("SELECT * FROM shop_list_name WHERE id = :listId" )
    suspend fun getListItemsByID (listId: Int): ShoppingListItem
    @Update
    suspend fun insertItem(item:ShoppingListItem)

}