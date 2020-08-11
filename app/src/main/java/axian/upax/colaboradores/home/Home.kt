package axian.upax.colaboradores.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import axian.upax.colaboradores.R
import axian.upax.colaboradores.home.add_collaborator.AddCollaboratorActivity
import axian.upax.colaboradores.home.show_collaborators.ShowCollaboratorsActivity
import kotlinx.android.synthetic.main.activity_home.*

class Home : AppCompatActivity() {

    private var nameUser: String? = null
    private var email: String? = null
    private var phoneNumber: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        nameUser = intent.extras?.getString("nameUser")
        email = intent.extras?.getString("email")
        phoneNumber = intent.extras?.getString("phoneNumber")
        
        btn_agregar_colaborador.setOnClickListener {
            startActivity(Intent(this, AddCollaboratorActivity::class.java))
        }

        btn_mis_colaboradores.setOnClickListener {
            startActivity(Intent(this, ShowCollaboratorsActivity::class.java))
        }
    }
}