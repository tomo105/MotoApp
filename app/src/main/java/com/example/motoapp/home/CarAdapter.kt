package com.example.motoapp.home

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.motoapp.R
import com.example.motoapp.data.Car
import com.example.motoapp.databinding.ListRowBinding

class CarAdapter(private val listener: OnCarItemLongClick) :
    RecyclerView.Adapter<CarAdapter.CarViewHolder>() {

    private val carsList = ArrayList<Car>()

    fun setCars(list: List<Car>) {
        carsList.clear()
        carsList.addAll(list)
        notifyDataSetChanged()                                                                              //inform adapter about changing data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val binding = ListRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CarViewHolder(binding)

        //  val inflater = LayoutInflater.from(parent.context)
        // val view = inflater.inflate(R.layout.list_row, parent, false)
        //  return CarViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        bindData(holder, position)

    }

    private fun bindData(holder: CarViewHolder, position: Int) {
        //  val name = holder.itemView.findViewById<TextView>(R.id.carName)
        //  val productionYear = holder.itemView.findViewById<TextView>(R.id.carProductionYear)
        //  val image = holder.itemView.findViewById<ImageView>(R.id.carImage)

        // name.text = carsList[holder.adapterPosition].name
        // productionYear.text = carsList[holder.adapterPosition].productionYear

        with(holder) {
            with(carsList[position]) {
                binding.carName.text = name
                binding.carProductionYear.text = productionYear
                // binding.carImage
                // car image TODO
            }
        }


    }

    override fun getItemCount(): Int {
        return carsList.size
    }

    inner class CarViewHolder(val binding: ListRowBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnLongClickListener() {
                Log.d("LOG_DEBUG", "elko w listenerze")
                listener.onCarLongClick(carsList[adapterPosition], adapterPosition)
                true
            }
        }

    }


    interface OnCarItemLongClick {
        fun onCarLongClick(car: Car, position: Int)
    }
}