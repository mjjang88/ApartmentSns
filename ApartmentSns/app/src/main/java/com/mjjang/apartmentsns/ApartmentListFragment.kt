package com.mjjang.apartmentsns

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.mjjang.apartmentsns.adapters.ApartmentAdapter
import com.mjjang.apartmentsns.databinding.FragmentApartmentListBinding
import com.mjjang.apartmentsns.utilities.InjectorUtils
import com.mjjang.apartmentsns.viewmodels.ApartmentListViewModel

class ApartmentListFragment : Fragment() {

    // TODO: 구현 필요.
    private val viewModel: ApartmentListViewModel by viewModels {
        InjectorUtils.provideApartmentListViewModelFactory(this)
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

        setHasOptionsMenu(true)
        return binding.root
    }

    private fun subscribeUi(adapter: ApartmentAdapter) {
        viewModel.apartments.observe(viewLifecycleOwner) { apartments ->
            adapter.submitList(apartments)
        }
    }
}