package com.tunehub.app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class ServiciosBasicosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_servicios_basicos)

        // Referencias
        val cardAceite = findViewById<LinearLayout>(R.id.cardAceite)
        val cardServicioMenor = findViewById<LinearLayout>(R.id.cardServicioMenor)
        val cardServicioMayor = findViewById<LinearLayout>(R.id.cardServicioMayor)
        val cardFrenos = findViewById<LinearLayout>(R.id.cardFrenos)
        val cardTrenDelantero = findViewById<LinearLayout>(R.id.cardTrenDelantero)
        val cardAlineacion = findViewById<LinearLayout>(R.id.cardAlineacion)
        val cardMecanica = findViewById<LinearLayout>(R.id.cardMecanica)
        val btnRegresar = findViewById<Button>(R.id.btnRegresar)

        // Función reutilizable
        fun irAgendar(servicio: String) {
            val intent = Intent(this, AgendarCitaActivity::class.java)
            intent.putExtra("servicio", servicio)
            startActivity(intent)
        }

        // Clicks
        cardAceite.setOnClickListener { irAgendar("Cambio de aceite") }
        cardServicioMenor.setOnClickListener { irAgendar("Servicio menor") }
        cardServicioMayor.setOnClickListener { irAgendar("Servicio mayor") }
        cardFrenos.setOnClickListener { irAgendar("Frenos") }
        cardTrenDelantero.setOnClickListener { irAgendar("Tren delantero") }
        cardAlineacion.setOnClickListener { irAgendar("Alineación y balanceo") }
        cardMecanica.setOnClickListener { irAgendar("Mecánica general") }

        btnRegresar.setOnClickListener {
            finish()
        }
    }
}