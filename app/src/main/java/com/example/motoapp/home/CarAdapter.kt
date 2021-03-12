package com.example.motoapp.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.motoapp.data.Car
import com.example.motoapp.databinding.ListRowBinding

class CarAdapter(private val listener: OnCarItemLongClick) : RecyclerView.Adapter<CarAdapter.CarViewHolder>() {

    private val LOG_DEBUG = "LOG_DEBUG"
    private val carsList = ArrayList<Car>()

    fun setCars(list: List<Car>) {
        carsList.clear()
        carsList.addAll(list)
        notifyDataSetChanged()                                                                              //inform adapter about changing data
    }
    fun removeCar(car : Car, position: Int) {
        carsList.remove(car)
        notifyItemRemoved(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val binding = ListRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CarViewHolder(binding)

    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        bindData(holder, position)

    }

    private fun bindData(holder: CarViewHolder, position: Int) {

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
                Log.d(LOG_DEBUG, "elko w listenerze")
                listener.onCarLongClick(carsList[adapterPosition], adapterPosition)
                true
            }
        }

    }


    interface OnCarItemLongClick {
        fun onCarLongClick(car: Car, position: Int)
    }
}