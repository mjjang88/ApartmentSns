package com.mjjang.apartmentsns.viewmodels

import androidx.lifecycle.*
import com.mjjang.apartmentsns.data.Apartment
import com.mjjang.apartmentsns.data.ApartmentRepository

class ApartmentListViewModel internal constructor(
    apartmnetRepository: ApartmentRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    //val apartments: LiveData<List<Apartment>> = apartmnetRepository.getApartmentList(searchWord)
    val searchWord: MutableLiveData<String?> = MutableLiveData(null)
    val apartments: LiveData<List<Apartment>> = Transformations.switchMap(searchWord) { id ->
        apartmnetRepository.getApartmentList(id)
    }
}