package com.tunehub.app

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class DetalleTuningActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_tuning)

        val container = findViewById<LinearLayout>(R.id.containerOpciones)

        val categoria = intent.getStringExtra("categoria") ?: ""

        when (categoria) {

            "performance" -> {
                agregarOpcion(container, "Stage 1", "Q1,500")
                agregarOpcion(container, "Stage 2", "Q3,000")
                agregarOpcion(container, "Stage 3", "Q6,000+")
            }

            "estetica" -> {
                agregarOpcion(container, "Pintura", "Q3,000+")
                agregarOpcion(container, "Wrap", "Q2,500+")
            }

            "suspension" -> {
                agregarOpcion(container, "Coilovers", "Q4,000")
                agregarOpcion(container, "Suspensión deportiva", "Q2,000")
            }

            "llantas" -> {
                agregarOpcion(container, "Rines deportivos", "Q2,500")
                agregarOpcion(container, "Llantas", "Q1,800")
            }
        }
    }

    private fun agregarOpcion(container: LinearLayout, nombre: String, precio: String) {

        val card = LinearLayout(this)
        card.orientation = LinearLayout.VERTICAL
        card.setPadding(32, 32, 32, 32)

        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(0, 0, 0, 24)
        card.layoutParams = params

        // Fondo tipo tarjeta
        card.setBackgroundColor(android.graphics.Color.parseColor("#1E293B"))

        // TÍTULO
        val titulo = TextView(this)
        titulo.text = nombre
        titulo.textSize = 18f
        titulo.setTypeface(null, android.graphics.Typeface.BOLD)
        titulo.setTextColor(android.graphics.Color.WHITE)

        // DESCRIPCIÓN (puedes mejorar esto después dinámicamente)
        val descripcion = TextView(this)
        descripcion.text = "Servicio profesional de $nombre"
        descripcion.textSize = 14f
        descripcion.setTextColor(android.graphics.Color.parseColor("#94A3B8"))

        // PRECIO
        val precioView = TextView(this)
        precioView.text = "Precio: $precio"
        precioView.textSize = 14f
        precioView.setTextColor(android.graphics.Color.parseColor("#22C55E"))

        // Agregar vistas a la card
        card.addView(titulo)
        card.addView(descripcion)
        card.addView(precioView)

        // CLICK → AGENDAR
        card.setOnClickListener {
            val intent = Intent(this, AgendarCitaActivity::class.java)
            intent.putExtra("servicio", nombre)
            startActivity(intent)
        }

        // Agregar card al contenedor
        container.addView(card)
    }
}