package com.tunehub.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.widget.Button

class ServiciosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_servicios)

        val btnAgendar = findViewById<Button>(R.id.btnAgendar)
        val btnVerServicios = findViewById<Button>(R.id.btnVerServicios)
        val btnRegresar = findViewById<Button>(R.id.btnRegresar)

        btnAgendar.setOnClickListener {
            val intent = Intent(this, AgendarCitaActivity::class.java)
            startActivity(intent)
        }

        btnVerServicios.setOnClickListener {
            val intent = Intent(this, TiposServiciosActivity::class.java)
            startActivity(intent)
        }

        btnRegresar.setOnClickListener {
            finish()
        }
    }
}