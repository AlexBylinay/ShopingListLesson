package pl.alexbul.shopinglistlesson.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.alexbul.shopinglistlesson.data.*
import pl.alexbul.shopinglistlesson.datactore.DataStoreManager
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideMainDb(app: Application): MainDb{
        return Room.databaseBuilder(app,
            MainDb::class.java,"shop_list").build()
    }
    @Provides
    @Singleton
    fun provideShopRepo(db:MainDb):ShoppingListRepository{
        return ShoppingListRepoImpl(db.shoppingListDao)
    }
    @Provides
    @Singleton
    fun provideAddItemRepo(db:MainDb):AddItemRepository{
        return AddItemRepoImp(db.addItemDao)
    }
    @Provides
    @Singleton
    fun provideNoteRepo(db:MainDb):NoteRepository{
        return NoteReposImp(db.noteDao)
    }

    @Provides
    @Singleton
    fun provideDataStoreManager(app:Application):DataStoreManager{
        return DataStoreManager(app)
    }


}