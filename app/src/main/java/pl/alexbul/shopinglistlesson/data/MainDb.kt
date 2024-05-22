package pl.alexbul.shopinglistlesson.data

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        ShoppingListItem::class,
        AddItem::class,
        NoteItem::class,
       // TestItem::class
               ],
    version = 1,
   // version = 2,
  //  autoMigrations = [AutoMigration(from = 1, to = 2)],
    exportSchema = true
)

abstract class MainDb : RoomDatabase() {
    abstract val shoppingListDao: ShoppingListDao
    abstract val noteDao: NoteDao
    abstract val addItemDao: AddItemDao
}