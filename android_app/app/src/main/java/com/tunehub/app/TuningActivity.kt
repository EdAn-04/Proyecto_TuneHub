package com.tunehub.app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class TuningActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tuning)

        val cardPerformance = findViewById<LinearLayout>(R.id.cardPerformance)
        val cardEstetica = findViewById<LinearLayout>(R.id.cardEstetica)
        val cardSuspension = findViewById<LinearLayout>(R.id.cardSuspension)
        val cardLlantas = findViewById<LinearLayout>(R.id.cardLlantas)
        val btnRegresar = findViewById<Button>(R.id.btnRegresar)

        // PERFORMANCE
        cardPerformance.setOnClickListener {
            val intent = Intent(this, DetalleTuningActivity::class.java)
            intent.putExtra("categoria", "performance")
            startActivity(intent)
        }

        // ESTÉTICA
        cardEstetica.setOnClickListener {
            val intent = Intent(this, DetalleTuningActivity::class.java)
            intent.putExtra("categoria", "estetica")
            startActivity(intent)
        }

        // SUSPENSIÓN
        cardSuspension.setOnClickListener {
            val intent = Intent(this, DetalleTuningActivity::class.java)
            intent.putExtra("categoria", "suspension")
            startActivity(intent)
        }

        // LLANTAS
        cardLlantas.setOnClickListener {
            val intent = Intent(this, DetalleTuningActivity::class.java)
            intent.putExtra("categoria", "llantas")
            startActivity(intent)
        }

        // REGRESAR
        btnRegresar.setOnClickListener {
            finish()
        }
    }
}