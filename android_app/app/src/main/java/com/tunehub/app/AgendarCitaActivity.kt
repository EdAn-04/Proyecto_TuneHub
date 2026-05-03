package com.tunehub.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.*
import android.app.DatePickerDialog
import android.content.Intent
import java.util.*

class AgendarCitaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_agendar_cita)

        // CAMPOS
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

        // RECIBIR SERVICIO
        val servicioRecibido = intent.getStringExtra("servicio")
        txtServicio.setText(servicioRecibido)
        txtServicio.isEnabled = false

        btnRegresar.setOnClickListener {
            finish()
        }

        // SELECCIONAR FECHA
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

        // GUARDAR CITA
        btnGuardar.setOnClickListener {

            val nombre = txtNombre.text.toString().trim()
            val servicio = txtServicio.text.toString().trim()
            val marca = txtMarca.text.toString().trim()
            val modelo = txtModelo.text.toString().trim()
            val anio = txtAnio.text.toString().trim()
            val placa = txtPlaca.text.toString().trim().uppercase()
            val fecha = txtFecha.text.toString()

            val regexPlaca = Regex("^[A-Z]{1}[0-9]{3}[A-Z]{3}$")

            // VALIDACIONES
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

            // GUARDAR CITA (SIN BD)
            val prefs = getSharedPreferences("citas", MODE_PRIVATE)
            val editor = prefs.edit()

            editor.putString("servicio", servicio)
            editor.putString("fecha", fecha)
            editor.putString("nombre", nombre)

            editor.apply()

            Toast.makeText(this, "Cita agendada correctamente", Toast.LENGTH_SHORT).show()

            // REGRESAR AL DASHBOARD
            val intent = Intent(this, DashboardActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            finish()
        }
    }
}