package com.mjjang.apartmentsns.utilities

import android.content.Context
import androidx.fragment.app.Fragment
import com.mjjang.apartmentsns.data.ApartmentRepository
import com.mjjang.apartmentsns.data.AppDatabase
import com.mjjang.apartmentsns.data.PostRepository
import com.mjjang.apartmentsns.viewmodels.ApartmentListViewModelFactory
import com.mjjang.apartmentsns.viewmodels.PostListViewModelFactory

object InjectorUtils {

    private fun getApartmentRepository(context: Context): ApartmentRepository {
        return ApartmentRepository.getInstance(
            AppDatabase.getInstance(context.applicationContext).apartmentDao()
        )
    }

    private fun getPostRepository(context: Context): PostRepository {
        return PostRepository.getInstance(
            AppDatabase.getInstance(context.applicationContext).postDao()
        )
    }

    fun provideApartmentListViewModelFactory(fragment: Fragment): ApartmentListViewModelFactory {
        val repository = getApartmentRepository(fragment.requireContext())
        return ApartmentListViewModelFactory(repository, fragment)
    }

    fun providePostListViewModelFactory(
        fragment: Fragment,
        apartmentId: Int
    ): PostListViewModelFactory {
        val apartmentRepository = getApartmentRepository(fragment.requireContext())
        val postRepository = getPostRepository(fragment.requireContext())
        return PostListViewModelFactory(apartmentRepository, postRepository, apartmentId, fragment)
    }
}