package com.mjjang.apartmentsns.utilities

import android.content.Context
import androidx.fragment.app.Fragment
import com.mjjang.apartmentsns.data.ApartmentRepository
import com.mjjang.apartmentsns.data.AppDatabase
import com.mjjang.apartmentsns.viewmodels.ApartmentListViewModelFactory

object InjectorUtils {

    private fun getApartmentRepository(context: Context): ApartmentRepository {
        return ApartmentRepository.getInstance(
            AppDatabase.getInstance(context.applicationContext).apartmentDao()
        )
    }

    fun provideApartmentListViewModelFactory(fragment: Fragment): ApartmentListViewModelFactory {
        val repository = getApartmentRepository(fragment.requireContext())
        return ApartmentListViewModelFactory(repository, fragment)
    }
}