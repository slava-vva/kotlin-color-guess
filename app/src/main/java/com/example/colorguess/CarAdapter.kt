package com.example.colorguess


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class Car(val id: Int, val make: String, val model: String, val year: Int)

class CarAdapter(
    private var carList: List<Car>,
    private val onItemClicked: (Car) -> Unit
) : RecyclerView.Adapter<CarAdapter.CarViewHolder>() {

    class CarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textMake: TextView = itemView.findViewById(R.id.textMake)
        val textModel: TextView = itemView.findViewById(R.id.textModel)
        val textYear: TextView = itemView.findViewById(R.id.textYear)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_car, parent, false)
        return CarViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        val car = carList[position]
        holder.textMake.text = car.make
        holder.textModel.text = car.model
        holder.textYear.text = car.year.toString()

        holder.itemView.setOnClickListener {
            onItemClicked(car)
        }
    }

    override fun getItemCount() = carList.size

    fun updateList(newList: List<Car>) {
        carList = newList
        notifyDataSetChanged()
    }
}
