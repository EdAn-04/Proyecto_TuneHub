package com.tunehub.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.*
import android.app.DatePickerDialog
import java.util.*
import android.widget.TextView
import android.widget.Button



class AgendarCitaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_agendar_cita)

        // TODOS los campos
        val txtNombre = findViewById<EditText>(R.id.txtNombre)
        val txtServicio = findViewById<EditText>(R.id.txtServicio)
        val txtMarca = findViewById<EditText>(R.id.txtMarca)
        val txtModelo = findViewById<EditText>(R.id.txtModelo)
        val txtAnio = findViewById<EditText>(R.id.txtAnio)
        val txtPlaca = findViewById<EditText>(R.id.txtPlaca)

        val btnFecha = findViewById<Button>(R.id.btnFecha)
        val txtFecha = findViewById<TextView>(R.id.txtFecha)
        val btnGuardar = findViewById<Button>(R.id.btnGuardar)
        val btnRegresar = findViewById<Button>(R.id.btnRegresar)

        btnRegresar.setOnClickListener {
            finish()
        }

        // FECHA
        btnFecha.setOnClickListener {

            val calendario = Calendar.getInstance()

            val datePicker = DatePickerDialog(
                this,
                { _, year, month, day ->
                    txtFecha.text = "$day/${month + 1}/$year"
                },
                calendario.get(Calendar.YEAR),
                calendario.get(Calendar.MONTH),
                calendario.get(Calendar.DAY_OF_MONTH)
            )

            datePicker.show()
        }

        // GUARDAR
        btnGuardar.setOnClickListener {

            val nombre = txtNombre.text.toString().trim()
            val servicio = txtServicio.text.toString().trim()
            val marca = txtMarca.text.toString().trim()
            val modelo = txtModelo.text.toString().trim()
            val anio = txtAnio.text.toString().trim()
            val placa = txtPlaca.text.toString().trim().uppercase()
            val fecha = txtFecha.text.toString()

            val regexPlaca = Regex("^[A-Z]{1}[0-9]{3}[A-Z]{3}$")

            if (
                nombre.isEmpty() ||
                servicio.isEmpty() ||
                marca.isEmpty() ||
                modelo.isEmpty() ||
                anio.isEmpty() ||
                placa.isEmpty() ||
                fecha == "Sin fecha"
            ) {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!regexPlaca.matches(placa)) {
                Toast.makeText(this, "Placa inválida (ej: P123ABC)", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Toast.makeText(this, "Cita agendada correctamente", Toast.LENGTH_SHORT).show()

            finish()
        }
    }
}