package com.mjjang.apartmentsns.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PostDao {
    @Query("SELECT * FROM posts WHERE apartment_id = :apartmentId ORDER BY post_id")
    fun getPosts(apartmentId: String): LiveData<List<Post>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(posts: List<Post>)
}