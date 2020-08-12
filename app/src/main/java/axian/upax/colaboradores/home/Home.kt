package axian.upax.colaboradores.home

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import axian.upax.colaboradores.R
import axian.upax.colaboradores.home.add_collaborator.AddCollaboratorActivity
import axian.upax.colaboradores.home.show_collaborators.ShowCollaboratorsActivity
import axian.upax.colaboradores.respository.remote.services.ContactApiService
import axian.upax.colaboradores.respository.remote.services.ResponseContactService
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import kotlinx.android.synthetic.main.activity_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Home : AppCompatActivity() {
    companion object {
        var BaseUrl = "https://dl.dropboxusercontent.com/s/5u21281sca8gj94/getFile.json?dl=0"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val signInAccount:GoogleSignInAccount? = GoogleSignIn.getLastSignedInAccount(this.applicationContext)
        if (signInAccount != null)
            tvCurrentName.text = signInAccount.displayName

        btn_agregar_colaborador.setOnClickListener { startActivity(Intent(this, AddCollaboratorActivity::class.java)) }

        btn_mis_colaboradores.setOnClickListener { startActivity(Intent(this, ShowCollaboratorsActivity::class.java)) }

        btn_add_from_file.setOnClickListener {
            getContactFromCloud()
        }
    }

    private fun getContactFromCloud() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(ContactApiService::class.java)
        val call = service.getContanctService()

        call.enqueue(object : Callback<ResponseContactService>{
            override fun onFailure(call: Call<ResponseContactService>?, t: Throwable?) {
                Toast.makeText(applicationContext, "La llamada al servicio falló", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<ResponseContactService>?, response: Response<ResponseContactService>?) {
                if (response!!.code() == 200){
                    val responseBody = response.body()
                    // Toast.makeText(applicationContext, "${responseBody.data}", Toast.LENGTH_SHORT).show()

                }
            }
        })
    }
}