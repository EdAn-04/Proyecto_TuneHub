package com.tunehub.app

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response

import org.json.JSONArray
import org.json.JSONObject

import java.io.IOException

class AsistenteIAActivity : AppCompatActivity() {

    private lateinit var txtChat: TextView
    private lateinit var txtPregunta: EditText
    private lateinit var btnEnviar: Button

    // 🔥 TU API KEY
    private val apiKey = " "

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_asistente_iaactivity)

        txtChat = findViewById(R.id.txtChat)
        txtPregunta = findViewById(R.id.txtPregunta)
        btnEnviar = findViewById(R.id.btnEnviar)

        btnEnviar.setOnClickListener {

            val pregunta = txtPregunta.text.toString().trim()

            if (pregunta.isEmpty()) {

                Toast.makeText(
                    this,
                    "Escribe una pregunta",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            txtChat.append(
                "\n👤 Tú:\n$pregunta\n"
            )

            enviarPreguntaIA(pregunta)

            txtPregunta.text.clear()
        }
    }

    private fun enviarPreguntaIA(
        pregunta: String
    ) {

        val client = OkHttpClient()

        val prompt = """
            Eres TuneBot, un asistente automotriz experto.

            Ayudas a usuarios con:
            - fallas mecánicas
            - modificaciones
            - mantenimiento
            - personalización de vehículos

            Responde de forma clara, profesional y breve.
            Máximo 2 párrafos.
            No uses JSON.
            No uses markdown.
            No uses símbolos extraños.

            Usuario:
            $pregunta
        """.trimIndent()

        val json = JSONObject()

        val parts = JSONArray()
        val part = JSONObject()

        part.put("text", prompt)

        parts.put(part)

        val content = JSONObject()
        content.put("parts", parts)

        val contents = JSONArray()
        contents.put(content)

        json.put("contents", contents)

        val body = RequestBody.create(
            "application/json".toMediaType(),
            json.toString()
        )

        val request = Request.Builder()
            .url(
                "https://generativelanguage.googleapis.com/v1beta/models/gemini-flash-latest:generateContent?key=$apiKey"
            )
            .post(body)
            .build()

        client.newCall(request)
            .enqueue(object : Callback {

                override fun onFailure(
                    call: Call,
                    e: IOException
                ) {

                    runOnUiThread {

                        txtChat.append(
                            "\n❌ Error de conexión:\n${e.message}\n"
                        )
                    }
                }

                override fun onResponse(
                    call: Call,
                    response: Response
                ) {

                    val respuesta = response.body?.string()

                    try {

                        val jsonRespuesta =
                            JSONObject(respuesta ?: "")

                        val candidates =
                            jsonRespuesta.optJSONArray("candidates")

                        if (
                            candidates != null &&
                            candidates.length() > 0
                        ) {

                            val content =
                                candidates
                                    .getJSONObject(0)
                                    .optJSONObject("content")

                            val parts =
                                content?.optJSONArray("parts")

                            val texto =
                                parts
                                    ?.getJSONObject(0)
                                    ?.optString("text")
                                    ?: "No se recibió respuesta"

                            runOnUiThread {

                                txtChat.append(
                                    "\n🤖 TuneBot:\n$texto\n"
                                )
                            }

                        } else {

                            val error =
                                jsonRespuesta.optJSONObject("error")

                            val mensajeError =
                                error?.optString("message")
                                    ?: "Gemini no devolvió respuesta"

                            runOnUiThread {

                                txtChat.append(
                                    "\n⚠️ $mensajeError\n"
                                )
                            }
                        }

                    } catch (e: Exception) {

                        runOnUiThread {

                            txtChat.append(
                                "\n⚠️ Error procesando respuesta\n"
                            )
                        }
                    }
                }
            })
    }
}