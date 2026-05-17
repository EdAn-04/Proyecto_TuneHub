package com.tunehub.app

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class AgregarVehiculoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_agregar_vehiculo)

        val txtMarca =
            findViewById<EditText>(R.id.txtMarca)

        val txtModelo =
            findViewById<EditText>(R.id.txtModelo)

        val txtAnio =
            findViewById<EditText>(R.id.txtAnio)

        val txtPlaca =
            findViewById<EditText>(R.id.txtPlaca)

        val btnGuardar =
            findViewById<Button>(R.id.btnGuardar)

        val btnRegresar =
            findViewById<Button>(R.id.btnRegresar)

        // FIREBASE
        val user = FirebaseAuth.getInstance().currentUser
        val db = FirebaseFirestore.getInstance()

        // GUARDAR
        btnGuardar.setOnClickListener {

            val marca =
                txtMarca.text.toString().trim()

            val modelo =
                txtModelo.text.toString().trim()

            val anio =
                txtAnio.text.toString().trim()

            val placa =
                txtPlaca.text.toString().trim().uppercase()

            // VALIDACIÓN
            if (
                marca.isEmpty() ||
                modelo.isEmpty() ||
                anio.isEmpty() ||
                placa.isEmpty()
            ) {

                Toast.makeText(
                    this,
                    "Completa todos los campos",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            if (user != null) {

                val vehiculo = hashMapOf(
                    "marca" to marca,
                    "modelo" to modelo,
                    "anio" to anio,
                    "placa" to placa
                )

                db.collection("usuarios")
                    .document(user.uid)
                    .collection("vehiculos")
                    .add(vehiculo)

                    .addOnSuccessListener {

                        Toast.makeText(
                            this,
                            "Vehículo guardado",
                            Toast.LENGTH_SHORT
                        ).show()

                        finish()
                    }

                    .addOnFailureListener {

                        Toast.makeText(
                            this,
                            "Error al guardar",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
            }
        }

        // REGRESAR
        btnRegresar.setOnClickListener {
            finish()
        }
    }
}