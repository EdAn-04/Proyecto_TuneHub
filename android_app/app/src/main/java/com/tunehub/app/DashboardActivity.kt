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

        cardVehiculos.setOnClickListener {
            val intent = Intent(this, VehiculosActivity::class.java)
            startActivity(intent)
        }
    }
}