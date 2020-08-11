package axian.upax.colaboradores.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import axian.upax.colaboradores.R
import axian.upax.colaboradores.home.add_collaborator.AddCollaboratorActivity
import axian.upax.colaboradores.home.show_collaborators.ShowCollaboratorsActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import kotlinx.android.synthetic.main.activity_home.*

class Home : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val signInAccount:GoogleSignInAccount? = GoogleSignIn.getLastSignedInAccount(this.applicationContext)
        if (signInAccount != null)
            tvCurrentName.text = signInAccount.displayName

        btn_agregar_colaborador.setOnClickListener { startActivity(Intent(this, AddCollaboratorActivity::class.java)) }

        btn_mis_colaboradores.setOnClickListener { startActivity(Intent(this, ShowCollaboratorsActivity::class.java)) }
    }
}