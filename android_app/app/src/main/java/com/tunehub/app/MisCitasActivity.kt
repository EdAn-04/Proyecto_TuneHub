package com.tunehub.app

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class MisCitasActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mis_citas)

        val txtCita = findViewById<TextView>(R.id.txtCita)

        val prefs = getSharedPreferences("citas", MODE_PRIVATE)

        val servicio = prefs.getString("servicio", null)
        val fecha = prefs.getString("fecha", null)

        if (servicio != null && fecha != null) {

            if (!fechaPasada(fecha)) {
                txtCita.text = "Servicio: $servicio\nFecha: $fecha"
            } else {
                prefs.edit().clear().apply()
                txtCita.text = "No tienes citas activas"
            }

        } else {
            txtCita.text = "No tienes citas"
        }
    }

    private fun fechaPasada(fecha: String): Boolean {
        val formato = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

        return try {
            val fechaCita = formato.parse(fecha)
            val hoy = Date()

            fechaCita != null && fechaCita.before(hoy)
        } catch (e: Exception) {
            false
        }
    }
}