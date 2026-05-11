package com.tunehub.app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class CitaAdapter(private val listaCitas: ArrayList<Cita>) :
    RecyclerView.Adapter<CitaAdapter.CitaViewHolder>() {

    class CitaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val txtServicio: TextView = itemView.findViewById(R.id.txtServicio)
        val txtVehiculo: TextView = itemView.findViewById(R.id.txtVehiculo)
        val txtFecha: TextView = itemView.findViewById(R.id.txtFecha)
        val txtEstado: TextView = itemView.findViewById(R.id.txtEstado)
        val btnEliminar: Button = itemView.findViewById(R.id.btnEliminar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CitaViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cita, parent, false)

        return CitaViewHolder(view)
    }

    override fun onBindViewHolder(holder: CitaViewHolder, position: Int) {

        val cita = listaCitas[position]

        holder.txtServicio.text = "🔧 ${cita.servicio}"
        holder.txtVehiculo.text = "🚗 ${cita.placa}"
        holder.txtFecha.text = "📅 ${cita.fecha}"
        holder.txtEstado.text = "📌 ${cita.estado}"

        holder.btnEliminar.setOnClickListener {

            val user = FirebaseAuth.getInstance().currentUser
            val db = FirebaseFirestore.getInstance()

            if (user != null) {

                db.collection("usuarios")
                    .document(user.uid)
                    .collection("citas")
                    .document(cita.id)
                    .delete()
                    .addOnSuccessListener {

                        listaCitas.removeAt(position)
                        notifyItemRemoved(position)

                        Toast.makeText(
                            holder.itemView.context,
                            "Cita eliminada",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    .addOnFailureListener {

                        Toast.makeText(
                            holder.itemView.context,
                            "Error al eliminar",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
            }
        }
    }

    override fun getItemCount(): Int {
        return listaCitas.size
    }
}