package axian.upax.colaboradores.home.show_collaborators

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import axian.upax.colaboradores.R

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class UbicationCollaboratorActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private var latitudContacto: Float?= null
    private var longitudContacto: Float?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ubication_collaborator)

        latitudContacto = intent.extras?.getFloat("latContact")
        longitudContacto = intent.extras?.getFloat("latContact")

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val location: LatLng = if (latitudContacto != null && longitudContacto != null){
            LatLng(latitudContacto!!.toDouble(), longitudContacto!!.toDouble())
        } else {
            // Localización default
            LatLng(-33.8667, 151.2)
        }
        Toast.makeText(this, "Latitud: $latitudContacto, Longitud: $longitudContacto", Toast.LENGTH_SHORT).show()
        Toast.makeText(this, "El mapa no se puede ver por que el API no está activado, temas con facturación.", Toast.LENGTH_SHORT).show()
        mMap.addMarker(MarkerOptions().position(location).title("Localización del contacto."))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(location))
    }
}