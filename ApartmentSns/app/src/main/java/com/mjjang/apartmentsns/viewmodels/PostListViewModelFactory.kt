package com.mjjang.apartmentsns.viewmodels

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.mjjang.apartmentsns.data.ApartmentRepository
import com.mjjang.apartmentsns.data.PostRepository

class PostListViewModelFactory(
    private val apartmentRepository: ApartmentRepository,
    private val postRepository: PostRepository,
    private val apartmentId: String,
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs){

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        return PostListViewModel(apartmentRepository, postRepository, apartmentId, handle) as T
    }
}