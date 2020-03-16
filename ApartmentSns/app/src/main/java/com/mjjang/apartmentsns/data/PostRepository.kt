package com.mjjang.apartmentsns.data

import com.mjjang.apartmentsns.network.ApartmentDBRetrofit
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

    private fun refreshPost() {
        ApartmentDBRetrofit.getService().requestPost().enqueue(object : Callback<List<Post>> {
            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
            }

            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.isSuccessful) {
                    GlobalScope.launch {
                        response.body()?.let { postDao.insertAll(it) };
                    }
                }
            }
        })
    }
}