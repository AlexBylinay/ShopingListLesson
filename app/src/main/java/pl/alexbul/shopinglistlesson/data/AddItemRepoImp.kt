package pl.alexbul.shopinglistlesson.data

import kotlinx.coroutines.flow.Flow

class AddItemRepoImp(private val dao: AddItemDao): AddItemRepository
{
    override suspend fun insertItem(item: AddItem) {
       dao.insertItem(item)
    }

    override suspend fun insertItem(item: ShoppingListItem) {
       dao.insertItem(item)
    }

    override suspend fun deleteItem(item: AddItem) {
       dao.deleteItem(item)
    }

    override fun getAllItemsByID(listId: Int): Flow<List<AddItem>> {
       return getAllItemsByID(listId)
    }

    override suspend fun getListItemsByID(listId: Int): ShoppingListItem {
        return dao.getListItemsByID(listId)
    }
}