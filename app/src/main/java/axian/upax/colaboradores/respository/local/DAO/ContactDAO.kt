package axian.upax.colaboradores.respository.local.DAO

import androidx.lifecycle.LiveData
import androidx.room.*
import axian.upax.colaboradores.respository.local.model.Contact

@Dao
interface ContactDao {
    @Insert
    fun insert(contact: Contact)

    @Update
    fun update(vararg contact: Contact)

    @Delete
    fun delete(vararg contact: Contact)

    @Query("SELECT * FROM " + Contact.TABLE_NAME + " ORDER BY name_contact")
    fun getOrderedAgenda(): LiveData<List<Contact>>
}