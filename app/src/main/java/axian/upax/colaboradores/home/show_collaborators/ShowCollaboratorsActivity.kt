package axian.upax.colaboradores.home.show_collaborators

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import axian.upax.colaboradores.R
import axian.upax.colaboradores.models.ItemListContact
import kotlinx.android.synthetic.main.activity_show_collaborators.*
import kotlinx.android.synthetic.main.item_contact.view.*

class ShowCollaboratorsActivity : AppCompatActivity() {

    private var mockListContacts: MutableList<ItemListContact> = mutableListOf()
    private lateinit var rvListContact: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_collaborators)

        // Create mock for contacts
        mockListContacts()

        // Validation if contacts exist
        if (mockListContacts.size != 0)
            tv_holder.visibility = View.GONE

        viewManager = LinearLayoutManager(this)
        viewAdapter = ContactAdapter(mockListContacts, this)

        rvListContact = findViewById<RecyclerView>(R.id.rvContacts).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    private fun mockListContacts(){
        mockListContacts.add(ItemListContact("Norman", "normansanchezn@gmail.com"))
        mockListContacts.add(ItemListContact("Norman", "normansanchezn@gmail.com"))
        mockListContacts.add(ItemListContact("Norman", "normansanchezn@gmail.com"))
        mockListContacts.add(ItemListContact("Norman", "normansanchezn@gmail.com"))
        mockListContacts.add(ItemListContact("Norman", "normansanchezn@gmail.com"))
        mockListContacts.add(ItemListContact("Norman", "normansanchezn@gmail.com"))
        mockListContacts.add(ItemListContact("Norman", "normansanchezn@gmail.com"))
    }

    inner class ContactAdapter(private val listContacts : MutableList<ItemListContact>, private val context: Context) : RecyclerView.Adapter<ViewHolder>() {

        override fun getItemCount(): Int = listContacts.size

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_contact, parent, false))

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.nameContact.text = listContacts[position].nameContact
            holder.contact.text = listContacts[position].contactContact
        }
    }

    inner class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val nameContact: TextView = view.tv_name_contact
        val contact: TextView = view.tv_contact
    }
}