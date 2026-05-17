package com.tunehub.app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Inicializar Firebase
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        // Referencias UI
        val txtEmail = findViewById<EditText>(R.id.txtEmail)
        val txtPassword = findViewById<EditText>(R.id.txtPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val btnRegister = findViewById<Button>(R.id.btnRegister)

        // LOGIN
        btnLogin.setOnClickListener {

            val email = txtEmail.text.toString().trim()
            val password = txtPassword.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {

                Toast.makeText(
                    this,
                    "Completa todos los campos",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->

                    if (task.isSuccessful) {

                        Toast.makeText(
                            this,
                            "Bienvenido",
                            Toast.LENGTH_SHORT
                        ).show()

                        startActivity(
                            Intent(
                                this,
                                DashboardActivity::class.java
                            )
                        )

                        finish()

                    } else {

                        Toast.makeText(
                            this,
                            "Error: ${task.exception?.message}",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }

        // REGISTRO
        btnRegister.setOnClickListener {

            val email = txtEmail.text.toString().trim()
            val password = txtPassword.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {

                Toast.makeText(
                    this,
                    "Completa todos los campos",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            if (password.length < 6) {

                Toast.makeText(
                    this,
                    "La contraseña debe tener al menos 6 caracteres",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->

                    if (task.isSuccessful) {

                        val user = auth.currentUser
                        val uid = user?.uid

                        val userMap = hashMapOf(
                            "email" to email,
                            "rol" to "cliente"
                        )

                        if (uid != null) {

                            db.collection("usuarios")
                                .document(uid)
                                .set(userMap)
                        }

                        Toast.makeText(
                            this,
                            "Cuenta creada",
                            Toast.LENGTH_SHORT
                        ).show()

                        startActivity(
                            Intent(
                                this,
                                DashboardActivity::class.java
                            )
                        )

                        finish()

                    } else {

                        Toast.makeText(
                            this,
                            "Error: ${task.exception?.message}",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }
    }

    // Mantener sesión iniciada
    override fun onStart() {
        super.onStart()

        val user = auth.currentUser

        if (user != null) {

            startActivity(
                Intent(
                    this,
                    DashboardActivity::class.java
                )
            )

            finish()
        }
    }
}