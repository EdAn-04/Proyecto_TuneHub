package com.tunehub.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.widget.LinearLayout
import android.widget.Button

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val cardVehiculos = findViewById<LinearLayout>(R.id.cardVehiculos)
        val cardServicios = findViewById<LinearLayout>(R.id.cardServicios)
        val btnMisCitas = findViewById<Button>(R.id.btnMisCitas)
        val btnIA = findViewById<Button>(R.id.btnIA)

        cardVehiculos.setOnClickListener {
            startActivity(Intent(this, VehiculosActivity::class.java))
        }

        cardServicios.setOnClickListener {
            startActivity(Intent(this, ServiciosActivity::class.java))
        }

        btnMisCitas.setOnClickListener {
            startActivity(Intent(this, MisCitasActivity::class.java))
        }

        btnIA.setOnClickListener {

            val intent =
                Intent(this, AsistenteIAActivity::class.java)

            startActivity(intent)
        }
    }
}