package axian.upax.colaboradores.respository.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = Contact.TABLE_NAME)
data class Contact(
    @ColumnInfo(name = "name_contact") @NotNull val nameContact: String,
    @ColumnInfo(name = "email_contact") @NotNull val emailContact: String,
    @ColumnInfo(name = "lat_contact") @NotNull val latContact: Float,
    @ColumnInfo(name = "lon_contact") @NotNull val lonContact: Float

) {
    companion object {
        const val TABLE_NAME = "contact"
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "contact_id")
    var contactId: Int = 0
}