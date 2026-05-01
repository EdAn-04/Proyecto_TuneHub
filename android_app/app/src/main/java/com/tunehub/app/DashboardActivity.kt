package com.tunehub.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.widget.LinearLayout

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val cardVehiculos = findViewById<LinearLayout>(R.id.cardVehiculos)
        val cardServicios = findViewById<LinearLayout>(R.id.cardServicios)

        cardVehiculos.setOnClickListener {
            val intent = Intent(this, VehiculosActivity::class.java)
            startActivity(intent)
        }

        cardServicios.setOnClickListener {
            val intent = Intent(this, ServiciosActivity::class.java)
            startActivity(intent)
        }
    }
}