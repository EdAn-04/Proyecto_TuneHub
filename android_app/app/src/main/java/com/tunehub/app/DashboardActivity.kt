package com.tunehub.app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val cardVehiculos =
            findViewById<LinearLayout>(R.id.cardVehiculos)

        val cardServicios =
            findViewById<LinearLayout>(R.id.cardServicios)

        val btnMisCitas =
            findViewById<Button>(R.id.btnMisCitas)

        val btnIA =
            findViewById<Button>(R.id.btnIA)

        val btnCerrarSesion =
            findViewById<Button>(R.id.btnCerrarSesion)

        val user =
            FirebaseAuth.getInstance().currentUser

        // SI NO HAY SESIÓN
        if (user == null) {

            val intent =
                Intent(this, LoginActivity::class.java)

            startActivity(intent)

            finish()
        }

        // VEHÍCULOS
        cardVehiculos.setOnClickListener {

            startActivity(
                Intent(
                    this,
                    VehiculosActivity::class.java
                )
            )
        }

        // SERVICIOS
        cardServicios.setOnClickListener {

            startActivity(
                Intent(
                    this,
                    ServiciosActivity::class.java
                )
            )
        }

        // MIS CITAS
        btnMisCitas.setOnClickListener {

            startActivity(
                Intent(
                    this,
                    MisCitasActivity::class.java
                )
            )
        }

        // IA
        btnIA.setOnClickListener {

            startActivity(
                Intent(
                    this,
                    AsistenteIAActivity::class.java
                )
            )
        }

        // CERRAR SESIÓN
        btnCerrarSesion.setOnClickListener {

            FirebaseAuth.getInstance().signOut()

            val intent =
                Intent(this, LoginActivity::class.java)

            intent.flags =
                Intent.FLAG_ACTIVITY_NEW_TASK or
                        Intent.FLAG_ACTIVITY_CLEAR_TASK

            startActivity(intent)

            finish()
        }
    }
}