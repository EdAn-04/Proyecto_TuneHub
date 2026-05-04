package com.tunehub.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.graphics.Color
import android.app.AlertDialog

class VehiculosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vehiculos)

        val btnAgregar = findViewById<Button>(R.id.btnAgregar)
        val contenedor = findViewById<LinearLayout>(R.id.contenedorVehiculos)

        btnAgregar.setOnClickListener {
            val intent = Intent(this, AgregarVehiculoActivity::class.java)
            startActivity(intent)
        }
        val btnRegresar = findViewById<Button>(R.id.btnRegresar)

        btnRegresar.setOnClickListener {
            finish()
        }

        // LIMPIAR CONTENEDOR
        contenedor.removeAllViews()

        // SI NO HAY VEHÍCULOS
        if (DataHolder.listaVehiculos.isEmpty()) {

            val emptyText = TextView(this)
            emptyText.text = "No tienes vehículos aún 🚗"
            emptyText.setTextColor(Color.GRAY)
            emptyText.textSize = 16f

            contenedor.addView(emptyText)

        } else {

            // RECORRER LISTA
            for (vehiculo in DataHolder.listaVehiculos) {

                val card = LinearLayout(this)
                card.orientation = LinearLayout.VERTICAL
                card.setPadding(40, 40, 40, 40)
                card.setBackgroundColor(Color.parseColor("#1E293B"))

                val params = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                params.setMargins(0, 0, 0, 30)
                card.layoutParams = params

                // 🏷 Marca + modelo
                val titulo = TextView(this)
                titulo.text = "${vehiculo.marca} ${vehiculo.modelo}"
                titulo.setTextColor(Color.WHITE)
                titulo.textSize = 18f

                // 📅 Año
                val anio = TextView(this)
                anio.text = "Año: ${vehiculo.anio}"
                anio.setTextColor(Color.LTGRAY)
                anio.textSize = 14f

                // 🔘 Botón eliminar individual
                val btnEliminar = Button(this)
                btnEliminar.text = "Eliminar"
                btnEliminar.setBackgroundColor(Color.parseColor("#E10600"))
                btnEliminar.setTextColor(Color.WHITE)

                btnEliminar.setOnClickListener {

                    AlertDialog.Builder(this)
                        .setTitle("Eliminar vehículo")
                        .setMessage("¿Seguro que deseas eliminar ${vehiculo.marca}?")
                        .setPositiveButton("Sí") { _, _ ->
                            DataHolder.listaVehiculos.remove(vehiculo)
                            recreate()
                        }
                        .setNegativeButton("Cancelar", null)
                        .show()
                }

                // 📦 Agregar a tarjeta
                card.addView(titulo)
                card.addView(anio)
                card.addView(btnEliminar)

                // 📦 Agregar al contenedor
                contenedor.addView(card)
            }
        }
    }
}