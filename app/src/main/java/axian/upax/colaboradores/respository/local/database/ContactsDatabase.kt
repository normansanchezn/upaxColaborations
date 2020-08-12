package axian.upax.colaboradores.respository.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import axian.upax.colaboradores.respository.local.DAO.ContactDao
import axian.upax.colaboradores.respository.local.model.Contact

@Database(entities = [Contact::class], version = 1)
abstract class ContactsDatabase : RoomDatabase() {
    abstract fun contactDao(): ContactDao

    companion object {
        private const val DATABASE_NAME = "contacts_database"
        @Volatile
        private var INSTANCE: ContactsDatabase? = null

        fun getInstance(context: Context): ContactsDatabase? {
            INSTANCE
                ?: synchronized(this) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    ContactsDatabase::class.java,
                    DATABASE_NAME
                ).build()
            }
            return INSTANCE
        }
    }
}