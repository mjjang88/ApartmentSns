package com.mjjang.apartmentsns.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mjjang.apartmentsns.HomeViewPagerFragment
import com.mjjang.apartmentsns.HomeViewPagerFragmentDirections
import com.mjjang.apartmentsns.data.Apartment
import com.mjjang.apartmentsns.databinding.ListItemApartmentBinding

class ApartmentListAdapter : ListAdapter<Apartment, RecyclerView.ViewHolder>(ApartmentDiffCallback()) {

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
                binding.apartment?.let { apartment ->
                    navigateToPostList(apartment, it) }
            }
        }

        private fun navigateToPostList(apartment: Apartment, view: View) {
            val direction = HomeViewPagerFragmentDirections.actionViewPagerFragmentToPostListFragment(
                apartment.id
            )
            view.findNavController().navigate(direction)
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
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Apartment, newItem: Apartment): Boolean {
        return oldItem == newItem
    }
}