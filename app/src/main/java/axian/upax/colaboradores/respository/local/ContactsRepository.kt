package axian.upax.colaboradores.respository.local

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import axian.upax.colaboradores.respository.local.DAO.ContactDao
import axian.upax.colaboradores.respository.local.database.ContactsDatabase
import axian.upax.colaboradores.respository.local.model.Contact

class ContactsRepository(application: Application) {
    private val contactDao: ContactDao? = ContactsDatabase.getInstance(application)?.contactDao()

    fun insert(contact: Contact) {
        if (contactDao != null) InsertAsyncTask(
            contactDao
        ).execute(contact)
    }

    fun getContacts(): LiveData<List<Contact>> {
        return contactDao?.getOrderedAgenda() ?: MutableLiveData<List<Contact>>()
    }

    private class InsertAsyncTask(private val contactDao: ContactDao) :
        AsyncTask<Contact, Void, Void>() {
        override fun doInBackground(vararg contacts: Contact?): Void? {
            for (contact in contacts) {
                if (contact != null) contactDao.insert(contact)
            }
            return null
        }
    }
}