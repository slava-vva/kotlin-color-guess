package com.example.colorguess

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MongoCarAdapter(
    private val cars: List<MongoCar>,
    private val onDelete: (MongoCar) -> Unit,
    private val onEdit: (MongoCar) -> Unit
) : RecyclerView.Adapter<MongoCarAdapter.CarViewHolder>() {

    inner class CarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtMake: TextView = itemView.findViewById(R.id.txtMake)
        val txtModel: TextView = itemView.findViewById(R.id.txtModel)
        val txtYear: TextView = itemView.findViewById(R.id.txtYear)
        val btnDelete: Button = itemView.findViewById(R.id.btnDelete)
        val btnEdit: Button = itemView.findViewById(R.id.btnEdit)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_mongo_car, parent, false)
        return CarViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        val car = cars[position]
        holder.txtMake.text = "Brand: ${car.brand}"
        holder.txtModel.text = "Model: ${car.model}"
        holder.txtYear.text = "Year: ${car.year}"

        holder.btnDelete.setOnClickListener { onDelete(car) }
        holder.btnEdit.setOnClickListener { onEdit(car) }
    }

    override fun getItemCount(): Int = cars.size
}