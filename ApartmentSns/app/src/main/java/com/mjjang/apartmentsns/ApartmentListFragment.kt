package com.mjjang.apartmentsns

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.mjjang.apartmentsns.adapters.ApartmentListAdapter
import com.mjjang.apartmentsns.databinding.FragmentApartmentListBinding
import com.mjjang.apartmentsns.manager.KeyboardManager
import com.mjjang.apartmentsns.utilities.InjectorUtils
import com.mjjang.apartmentsns.viewmodels.ApartmentListViewModel

class ApartmentListFragment : Fragment() {

    private val viewModel: ApartmentListViewModel by viewModels {
        InjectorUtils.provideApartmentListViewModelFactory(this)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        saveInstanceState: Bundle?
    ): View? {
        val binding = FragmentApartmentListBinding.inflate(inflater, container, false)
        context ?: return binding.root

        val adapter = ApartmentListAdapter()
        binding.apartmentList.adapter = adapter
        subscribeUi(adapter)

        setHasOptionsMenu(true)

        binding.apartmentList.setOnTouchListener { view, motionEvent ->
            when(motionEvent.action) {
                MotionEvent.ACTION_MOVE -> {KeyboardManager.hideKeyboard(context, view)}
            }
            false
        }
        return binding.root
    }

    private fun subscribeUi(adapter: ApartmentListAdapter) {
        viewModel.apartments.observe(viewLifecycleOwner) { apartments ->
            adapter.submitList(apartments)
        }
    }
}