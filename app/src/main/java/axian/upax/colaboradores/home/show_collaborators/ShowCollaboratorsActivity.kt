package axian.upax.colaboradores.home.show_collaborators

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import axian.upax.colaboradores.R
import axian.upax.colaboradores.models.ItemListContact
import axian.upax.colaboradores.respository.ContactsRepository
import axian.upax.colaboradores.respository.model.Contact
import kotlinx.android.synthetic.main.activity_show_collaborators.*
import kotlinx.android.synthetic.main.item_contact.view.*

class ShowCollaboratorsActivity : AppCompatActivity() {

    private var mockListContacts: MutableList<ItemListContact> = mutableListOf()
    private var listContactsFromDb: MutableList<ItemListContact> = mutableListOf()
    private lateinit var rvListContact: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private var repository: ContactsRepository? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_collaborators)

        // Create mock for contacts
        // mockListContacts()

        // Get contacts from database
        repository = ContactsRepository(application)
        val contacts = repository?.getContacts()


        // Validation if contacts exist
        // if (mockListContacts.size != 0) tv_holder.visibility = View.GONE
        if (contacts?.value != null){
            tv_holder.visibility = View.GONE
            contacts?.value!!.forEach { contact ->
                listContactsFromDb.add(ItemListContact(
                    contacts.value!![contact.contactId].nameContact,
                    contacts.value!![contact.contactId].emailContact,
                    contacts.value!![contact.contactId].latContact,
                    contacts.value!![contact.contactId].lonContact
                ))
            }
        }

        viewManager = LinearLayoutManager(this)
        // viewAdapter = ContactAdapter(mockListContacts, this)
        viewAdapter = ContactAdapter(listContactsFromDb, this)
        rvListContact = findViewById<RecyclerView>(R.id.rvContacts).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        this.finish()
    }

    private fun mockListContacts(){
        mockListContacts.add(ItemListContact("Norman", "normansanchezn@gmail.com",19.42847f,-99.12766f))
        mockListContacts.add(ItemListContact("Norman", "normansanchezn@gmail.com",19.42847f,-99.12766f))
        mockListContacts.add(ItemListContact("Norman", "normansanchezn@gmail.com",19.42847f,-99.12766f))
        mockListContacts.add(ItemListContact("Norman", "normansanchezn@gmail.com",19.42847f,-99.12766f))
        mockListContacts.add(ItemListContact("Norman", "normansanchezn@gmail.com",19.42847f,-99.12766f))
        mockListContacts.add(ItemListContact("Norman", "normansanchezn@gmail.com",19.42847f,-99.12766f))
        mockListContacts.add(ItemListContact("Norman", "normansanchezn@gmail.com",19.42847f,-99.12766f))
        mockListContacts.add(ItemListContact("Norman", "normansanchezn@gmail.com",19.42847f,-99.12766f))
        mockListContacts.add(ItemListContact("Norman", "normansanchezn@gmail.com",19.42847f,-99.12766f))
        mockListContacts.add(ItemListContact("Norman", "normansanchezn@gmail.com",19.42847f,-99.12766f))
        mockListContacts.add(ItemListContact("Norman", "normansanchezn@gmail.com",19.42847f,-99.12766f))
        mockListContacts.add(ItemListContact("Norman", "normansanchezn@gmail.com",19.42847f,-99.12766f))
        mockListContacts.add(ItemListContact("Norman", "normansanchezn@gmail.com",19.42847f,-99.12766f))
        mockListContacts.add(ItemListContact("Norman", "normansanchezn@gmail.com",19.42847f,-99.12766f))
        mockListContacts.add(ItemListContact("Norman", "normansanchezn@gmail.com",19.42847f,-99.12766f))
        mockListContacts.add(ItemListContact("Norman", "normansanchezn@gmail.com",19.42847f,-99.12766f))
    }

    inner class ContactAdapter(private val listContacts : MutableList<ItemListContact>, private val context: Context) : RecyclerView.Adapter<ViewHolder>() {

        override fun getItemCount(): Int = listContacts.size

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_contact, parent, false))

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.nameContact.text = listContacts[position].nameContact
            holder.contact.text = listContacts[position].contactContact

            holder.itemView.setOnClickListener {
                val intentMaps = Intent(context, UbicationCollaboratorActivity::class.java)
                intentMaps.extras?.putFloat("latContact", listContacts[position].latContact)
                intentMaps.extras?.putFloat("latContact", listContacts[position].longContact)
                startActivity(intentMaps)
            }
        }
    }

    inner class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val nameContact: TextView = view.tv_name_contact
        val contact: TextView = view.tv_contact
    }
}