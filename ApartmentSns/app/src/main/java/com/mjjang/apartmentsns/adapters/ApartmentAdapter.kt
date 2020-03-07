package com.mjjang.apartmentsns.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mjjang.apartmentsns.data.Apartment
import com.mjjang.apartmentsns.databinding.ListItemApartmentBinding

class ApartmentAdapter : ListAdapter<Apartment, RecyclerView.ViewHolder>(ApartmentDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ApartmentViewHolder(ListItemApartmentBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val apartment = getItem(position)
        (holder as ApartmentViewHolder).bind(apartment)
    }

    class ApartmentViewHolder(
        private val binding : ListItemApartmentBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {
                binding.apartment?.let { TODO("항목 클릭 했을 때 동작을 추가") }
            }
        }

        fun bind(item: Apartment) {
            binding.apply {
                apartment = item
                executePendingBindings()
            }
        }
    }
}

private class ApartmentDiffCallback : DiffUtil.ItemCallback<Apartment>() {

    override fun areItemsTheSame(oldItem: Apartment, newItem: Apartment): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Apartment, newItem: Apartment): Boolean {
        return oldItem == newItem
    }
}