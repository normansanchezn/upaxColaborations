package axian.upax.colaboradores.home.add_collaborator

import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import android.widget.Toast.makeText
import androidx.appcompat.app.AppCompatActivity
import axian.upax.colaboradores.R
import axian.upax.colaboradores.respository.ContactsRepository
import axian.upax.colaboradores.respository.model.Contact
import kotlinx.android.synthetic.main.activity_add_collaborator.*

class AddCollaboratorActivity : AppCompatActivity(){

    private var repository: ContactsRepository? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_collaborator)
        repository = ContactsRepository(application)

        btn_agregar_colaborador.setOnClickListener {
            val nameContact = etNombreContacto.text.toString()
            val contact = etContacto.text.toString()

            when {
                nameContact.isEmpty() -> makeText(this, "El nombre no puede estar vacío", LENGTH_SHORT).show()
                contact.isEmpty() -> makeText(this, "El contacto no puede estar vacío", LENGTH_SHORT).show()
                else -> {
                    saveContact(Contact(nameContact, contact, 19.4978f, -99.1269f))
                    makeText(this, "Contacto guardado correctamente.", LENGTH_SHORT).show()
                    this.finish()
                }
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        this.finish()
    }

    private fun saveContact(contact: Contact) {
        repository?.insert(contact)
    }
}