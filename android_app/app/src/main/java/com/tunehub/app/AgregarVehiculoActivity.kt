package com.tunehub.app

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.EditText
import android.widget.Button
import android.widget.Toast
import com.tunehub.app.Vehiculo
import com.tunehub.app.DataHolder

class AgregarVehiculoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_vehiculo)

        val txtMarca = findViewById<EditText>(R.id.txtMarca)
        val txtModelo = findViewById<EditText>(R.id.txtModelo)
        val txtAnio = findViewById<EditText>(R.id.txtAnio)
        val btnGuardar = findViewById<Button>(R.id.btnGuardar)

        btnGuardar.setOnClickListener {

            val marca = txtMarca.text.toString().trim()
            val modelo = txtModelo.text.toString().trim()
            val anio = txtAnio.text.toString().trim()

            // VALIDACIÓN
            if (marca.isEmpty() || modelo.isEmpty() || anio.isEmpty()) {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // CREAR OBJETO
            val vehiculo = Vehiculo(marca, modelo, anio)

            // GUARDAR EN LISTA
            DataHolder.listaVehiculos.add(vehiculo)

            // REGRESAR
            finish()
        }
    }
}