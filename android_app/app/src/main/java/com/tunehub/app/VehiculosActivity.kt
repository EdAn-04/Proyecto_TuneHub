package com.tunehub.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.widget.Button
import android.app.AlertDialog

class VehiculosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vehiculos)

        val btnAgregar = findViewById<Button>(R.id.btnAgregar)

        btnAgregar.setOnClickListener {
            val intent = Intent(this, AgregarVehiculoActivity::class.java)
            startActivity(intent)
        }

        val btnEliminar = findViewById<Button>(R.id.btnEliminar)

        btnEliminar.setOnClickListener {

            if (DataHolder.listaVehiculos.isEmpty()) {
                return@setOnClickListener
            }

            AlertDialog.Builder(this)
                .setTitle("Eliminar vehículo")
                .setMessage("¿Eliminar el primer vehículo de la lista?")
                .setPositiveButton("Sí") { dialog, which ->
                    DataHolder.listaVehiculos.removeAt(0)
                    recreate()
                }
                .setNegativeButton("Cancelar", null)
                .show()
        }

    }
}