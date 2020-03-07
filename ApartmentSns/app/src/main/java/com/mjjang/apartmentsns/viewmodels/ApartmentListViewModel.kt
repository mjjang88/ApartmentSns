package com.mjjang.apartmentsns.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.mjjang.apartmentsns.data.Apartment
import com.mjjang.apartmentsns.data.ApartmentRepository

class ApartmentListViewModel internal constructor(
    apartmnetRepository: ApartmentRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val apartments: LiveData<List<Apartment>> = apartmnetRepository.getApartments()
}