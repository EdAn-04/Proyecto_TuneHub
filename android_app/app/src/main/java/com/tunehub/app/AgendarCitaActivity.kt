package com.tunehub.app

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

class AgendarCitaActivity : AppCompatActivity() {

    private lateinit var progressBar: ProgressBar

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

        progressBar = findViewById(R.id.progressBar)

        // FIREBASE
        val auth = FirebaseAuth.getInstance()
        val db = FirebaseFirestore.getInstance()

        // SERVICIO
        val servicioRecibido = intent.getStringExtra("servicio")
        txtServicio.setText(servicioRecibido ?: "")
        txtServicio.isEnabled = false

        // REGRESAR
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

        // GUARDAR CITA
        btnGuardar.setOnClickListener {

            val nombre = txtNombre.text.toString().trim()
            val servicio = txtServicio.text.toString().trim()
            val marca = txtMarca.text.toString().trim()
            val modelo = txtModelo.text.toString().trim()
            val anio = txtAnio.text.toString().trim()
            val placa = txtPlaca.text.toString().trim().uppercase()
            val fecha = txtFecha.text.toString().trim()

            val regexPlaca = Regex("^[A-Z]{1}[0-9]{3}[A-Z]{3}$")

            // VALIDACIÓN
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

            val user = auth.currentUser

            if (user == null) {
                Toast.makeText(this, "Usuario no autenticado", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            // UI LOADING
            progressBar.visibility = View.VISIBLE
            btnGuardar.isEnabled = false

            val cita = hashMapOf(
                "nombre" to nombre,
                "servicio" to servicio,
                "marca" to marca,
                "modelo" to modelo,
                "anio" to anio,
                "placa" to placa,
                "fecha" to fecha,
                "estado" to "pendiente"
            )

            db.collection("usuarios")
                .document(user.uid)
                .collection("citas")
                .add(cita)
                .addOnSuccessListener {

                    progressBar.visibility = View.GONE
                    btnGuardar.isEnabled = true

                    val dialog = AlertDialog.Builder(this)
                    dialog.setTitle("🎉 Cita agendada")
                    dialog.setMessage("Tu cita fue registrada correctamente.")

                    dialog.setPositiveButton("Aceptar") { d, _ ->

                        val intent = Intent(this, DashboardActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                        startActivity(intent)

                        finish()
                        d.dismiss()
                    }

                    dialog.setCancelable(false)
                    dialog.show()
                }
                .addOnFailureListener { e ->

                    progressBar.visibility = View.GONE
                    btnGuardar.isEnabled = true

                    Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_LONG).show()
                }
        }
    }
}