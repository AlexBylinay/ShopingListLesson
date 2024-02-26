package pl.alexbul.shopinglistlesson.data

import kotlinx.coroutines.flow.Flow

class NoteReposImp (private val dao: NoteDao): NoteRepository {
    override suspend fun insertItem(item: NoteItem) {
       dao.insertItem(item)
    }

    override suspend fun deleteItem(item: NoteItem) {
        dao.deleteItem(item)
    }

    override fun getAllItems(): Flow<List<NoteItem>> {
      return dao.getAllItems()
    }

    override suspend fun getNoteItemsByID(id: Int): NoteItem {
       return dao.getNoteItemsByID(id)
    }
}