package com.mjjang.apartmentsns.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.mjjang.apartmentsns.data.Apartment
import com.mjjang.apartmentsns.data.ApartmentRepository
import com.mjjang.apartmentsns.data.Post
import com.mjjang.apartmentsns.data.PostRepository

class PostListViewModel internal constructor(
    apartmentRepository: ApartmentRepository,
    postRepository: PostRepository,
    private val apartmentId: Int,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val apartment: LiveData<Apartment> = apartmentRepository.getApartment(apartmentId)
    val posts: LiveData<List<Post>> = postRepository.getPosts(apartmentId)
}