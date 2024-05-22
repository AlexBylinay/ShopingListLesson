package pl.alexbul.shopinglistlesson.datactore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.protobuf.StringValue
import kotlinx.coroutines.flow.map

const val DATA_STORE_NAME = "preference_storage_name"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(DATA_STORE_NAME)

class DataStoreManager(val context: Context) {

    suspend fun saveStringPreference(value: String, key:String ){
        context.dataStore.edit { pref-> pref[stringPreferencesKey(key)] = value}
    }

    fun getStringPreference (key: String, defVal:String) =
        context.dataStore.data.map {pref -> pref [stringPreferencesKey(key)]?: defVal }

    companion object{
        const val TITLE_COLOR = "title_color"
    }
}