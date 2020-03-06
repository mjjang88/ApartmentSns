package com.mjjang.apartmentsns

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mjjang.apartmentsns.adapters.ApartmentAdapter
import com.mjjang.apartmentsns.databinding.FragmentApartmentListBinding

class ApartmentListFragment : Fragment() {

    // TODO: 구현 필요.
    private val viewModel: ApartmentListViewModel by viewModels {

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        saveInstanceState: Bundle?
    ): View? {
        val binding = FragmentApartmentListBinding.inflate(inflater, container, false)
        context ?: return binding.root

        val adapter = ApartmentAdapter()
        binding.apartmentList.adapter = adapter
        subscribeUi(adapter)

    }

    private fun subscribeUi(adapter: ApartmentAdapter) {
        viewModel.apartments.observe
    }
}