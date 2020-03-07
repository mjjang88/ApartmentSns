package com.mjjang.apartmentsns.data

class PostRepository private constructor(
    private val postDao : PostDao
) {

    fun getPosts(apartmentId: Int) = postDao.getPosts(apartmentId)

    companion object {
        @Volatile private var instance: PostRepository? = null

        fun getInstance(postDao: PostDao) =
            instance ?: synchronized(this) {
                instance ?: PostRepository(postDao).also { instance = it }
            }
    }
}