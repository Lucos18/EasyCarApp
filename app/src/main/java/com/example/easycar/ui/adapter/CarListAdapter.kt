package com.example.easycar.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.easycar.databinding.CarItemCardBinding
import com.example.easycar.model.Car

class CarListAdapter(
    private val clickListener: (Car) -> Unit
) : ListAdapter<Car, CarListAdapter.CarViewHolder>(DiffCallback) {

    class CarViewHolder(
        private var binding: CarItemCardBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(car: Car) {
            binding.carBrandNameCard.text = car.brand
            binding.carModelNameCard.text = car.model
            //TODO add image implementation
            binding.carYearStartProductionCard.text = car.yearStartProduction.toString()
            binding.carFuelTypeCard.text = car.fuelType
            binding.executePendingBindings()
        }
    }
    companion object DiffCallback: DiffUtil.ItemCallback<Car>() {
        override fun areItemsTheSame(oldItem: Car, newItem: Car): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Car, newItem: Car): Boolean {
            return oldItem == newItem
        }

    }
    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        val car = getItem(position)
        holder.itemView.setOnClickListener{
            clickListener(car)
        }
        holder.bind(car)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CarViewHolder(
            CarItemCardBinding.inflate(layoutInflater, parent, false)
        )
    }
}