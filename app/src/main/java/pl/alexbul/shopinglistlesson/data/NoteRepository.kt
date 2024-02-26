package pl.alexbul.shopinglistlesson.data

import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    suspend fun insertItem(item:NoteItem)
    suspend fun deleteItem(item:NoteItem)
    fun getAllItems (): Flow<List<NoteItem>>
    suspend fun getNoteItemsByID (id: Int): NoteItem
}