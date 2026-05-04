package com.tunehub.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.widget.Button
import android.widget.LinearLayout

class ServiciosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_servicios)

        val cardServicios = findViewById<LinearLayout>(R.id.cardServicios)
        val cardTuning = findViewById<LinearLayout>(R.id.cardTuning)
        val btnRegresar = findViewById<Button>(R.id.btnRegresar)

        cardServicios.setOnClickListener {
            startActivity(Intent(this, ServiciosBasicosActivity::class.java))
        }

        cardTuning.setOnClickListener {
            startActivity(Intent(this, TuningActivity::class.java))
        }

        btnRegresar.setOnClickListener {
            finish()
        }
    }
}