package com.mjjang.apartmentsns.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ApartmentDao {
    @Query("SELECT * FROM apartments ORDER BY name")
    fun getApartment(): LiveData<List<Apartment>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(apartments: List<Apartment>)
}