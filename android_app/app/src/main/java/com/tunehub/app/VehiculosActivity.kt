package com.tunehub.app

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.app.AlertDialog
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class VehiculosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_vehiculos)

        val btnAgregar = findViewById<Button>(R.id.btnAgregar)
        val btnRegresar = findViewById<Button>(R.id.btnRegresar)
        val contenedor = findViewById<LinearLayout>(R.id.contenedorVehiculos)

        // FIREBASE
        val user = FirebaseAuth.getInstance().currentUser
        val db = FirebaseFirestore.getInstance()

        // AGREGAR VEHÍCULO
        btnAgregar.setOnClickListener {

            val intent = Intent(this, AgregarVehiculoActivity::class.java)
            startActivity(intent)
        }

        // REGRESAR
        btnRegresar.setOnClickListener {
            finish()
        }

        // LIMPIAR CONTENEDOR
        contenedor.removeAllViews()

        if (user != null) {

            db.collection("usuarios")
                .document(user.uid)
                .collection("vehiculos")
                .get()
                .addOnSuccessListener { result ->

                    contenedor.removeAllViews()

                    // SI NO HAY VEHÍCULOS
                    if (result.isEmpty) {

                        val emptyText = TextView(this)
                        emptyText.text = "No tienes vehículos aún 🚗"
                        emptyText.setTextColor(Color.GRAY)
                        emptyText.textSize = 16f

                        contenedor.addView(emptyText)

                    } else {

                        // RECORRER VEHÍCULOS
                        for (doc in result) {

                            val marca =
                                doc.getString("marca") ?: "N/A"

                            val modelo =
                                doc.getString("modelo") ?: "N/A"

                            val anio =
                                doc.getString("anio") ?: "N/A"

                            val placa =
                                doc.getString("placa") ?: "N/A"

                            val card = LinearLayout(this)
                            card.orientation = LinearLayout.VERTICAL
                            card.setPadding(40, 40, 40, 40)
                            card.setBackgroundColor(
                                Color.parseColor("#1E293B")
                            )

                            val params = LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT
                            )

                            params.setMargins(0, 0, 0, 30)
                            card.layoutParams = params

                            // MARCA + MODELO
                            val titulo = TextView(this)
                            titulo.text = "$marca $modelo"
                            titulo.setTextColor(Color.WHITE)
                            titulo.textSize = 18f

                            // AÑO
                            val txtAnio = TextView(this)
                            txtAnio.text = "Año: $anio"
                            txtAnio.setTextColor(Color.LTGRAY)
                            txtAnio.textSize = 14f

                            // PLACA
                            val txtPlaca = TextView(this)
                            txtPlaca.text = "Placa: $placa"
                            txtPlaca.setTextColor(Color.LTGRAY)
                            txtPlaca.textSize = 14f

                            // BOTÓN ELIMINAR
                            val btnEliminar = Button(this)

                            btnEliminar.text = "Eliminar"

                            btnEliminar.setBackgroundColor(
                                Color.parseColor("#E10600")
                            )

                            btnEliminar.setTextColor(Color.WHITE)

                            btnEliminar.setOnClickListener {

                                AlertDialog.Builder(this)
                                    .setTitle("Eliminar vehículo")
                                    .setMessage(
                                        "¿Seguro que deseas eliminar $marca?"
                                    )

                                    .setPositiveButton("Sí") { _, _ ->

                                        db.collection("usuarios")
                                            .document(user.uid)
                                            .collection("vehiculos")
                                            .document(doc.id)
                                            .delete()
                                            .addOnSuccessListener {

                                                Toast.makeText(
                                                    this,
                                                    "Vehículo eliminado",
                                                    Toast.LENGTH_SHORT
                                                ).show()

                                                recreate()
                                            }
                                    }

                                    .setNegativeButton(
                                        "Cancelar",
                                        null
                                    )

                                    .show()
                            }

                            // AGREGAR A CARD
                            card.addView(titulo)
                            card.addView(txtAnio)
                            card.addView(txtPlaca)
                            card.addView(btnEliminar)

                            // AGREGAR AL CONTENEDOR
                            contenedor.addView(card)
                        }
                    }
                }

                .addOnFailureListener {

                    Toast.makeText(
                        this,
                        "Error al cargar vehículos",
                        Toast.LENGTH_SHORT
                    ).show()
                }
        }
    }
}