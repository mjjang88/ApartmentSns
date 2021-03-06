package com.mjjang.apartmentsns.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ApartmentDao {
    @Query("SELECT * FROM apartments ORDER BY name asc")
    fun getApartmentList(): LiveData<List<Apartment>>

    @Query("SELECT * FROM apartments WHERE name like '%' || :searchWord || '%' ORDER BY name asc")
    fun getApartmentList(searchWord: String): LiveData<List<Apartment>>

    @Query("SELECT * FROM apartments WHERE id = :apartmentId")
    fun getApartment(apartmentId: String): LiveData<Apartment>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(apartments: List<Apartment>)
}