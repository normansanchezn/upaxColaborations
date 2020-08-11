package axian.upax.colaboradores.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import axian.upax.colaboradores.R
import axian.upax.colaboradores.home.Home
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {

    private var RC_SIGN_IN: Int = 10001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val providers = arrayListOf(
            AuthUI.IdpConfig.GoogleBuilder().build())

        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build(),
            RC_SIGN_IN)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)

            if (resultCode == Activity.RESULT_OK) {
                val user = FirebaseAuth.getInstance().currentUser
                val homeIntent = Intent(this, Home::class.java)
                homeIntent.putExtra("email", response?.email)
                homeIntent.putExtra("phoneNumber", response?.phoneNumber)
                homeIntent.putExtra("nameUser", user?.displayName)
                Toast.makeText(this, "$user", Toast.LENGTH_LONG).show()
                startActivity(homeIntent)
            } else{
                Toast.makeText(this, "Hubo un error en la autenticación.", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "No se pudo crear la conexión con tu cuenta de Google.", Toast.LENGTH_SHORT).show()
        }
    }
}