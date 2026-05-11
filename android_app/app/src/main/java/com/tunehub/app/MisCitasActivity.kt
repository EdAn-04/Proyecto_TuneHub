package com.tunehub.app

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MisCitasActivity : AppCompatActivity() {

    private lateinit var recyclerCitas: RecyclerView
    private lateinit var listaCitas: ArrayList<Cita>
    private lateinit var adapter: CitaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mis_citas)

        recyclerCitas = findViewById(R.id.recyclerCitas)
        val btnRegresar = findViewById<Button>(R.id.btnRegresar)

        recyclerCitas.layoutManager = LinearLayoutManager(this)
        recyclerCitas.setHasFixedSize(true)

        listaCitas = ArrayList()
        adapter = CitaAdapter(listaCitas)

        recyclerCitas.adapter = adapter

        btnRegresar.setOnClickListener {
            finish()
        }

        val user = FirebaseAuth.getInstance().currentUser
        val db = FirebaseFirestore.getInstance()

        if (user != null) {

            db.collection("usuarios")
                .document(user.uid)
                .collection("citas")
                .get()
                .addOnSuccessListener { result ->

                    listaCitas.clear()

                    for (doc in result) {

                        val cita = Cita(
                            id = doc.id,
                            servicio = doc.getString("servicio") ?: "N/A",
                            fecha = doc.getString("fecha") ?: "N/A",
                            estado = doc.getString("estado") ?: "N/A",
                            placa = doc.getString("placa") ?: "N/A"
                        )

                        listaCitas.add(cita)
                    }

                    adapter.notifyDataSetChanged()

                    if (listaCitas.isEmpty()) {
                        Toast.makeText(
                            this,
                            "No tienes citas registradas",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                .addOnFailureListener {

                    Toast.makeText(
                        this,
                        "Error al cargar las citas",
                        Toast.LENGTH_SHORT
                    ).show()
                }

        } else {

            Toast.makeText(
                this,
                "Usuario no autenticado",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
