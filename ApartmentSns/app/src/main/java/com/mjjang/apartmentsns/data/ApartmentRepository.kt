package com.mjjang.apartmentsns.data

import androidx.lifecycle.LiveData
import com.mjjang.apartmentsns.network.ApartmentDBRetrofit
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApartmentRepository private constructor(
    private val apartmentDao : ApartmentDao
) {

    fun getApartmentList() : LiveData<List<Apartment>> {
        refreshApartment()
        return apartmentDao.getApartmentList()
    }
    fun getApartment(apartmentId: String) = apartmentDao.getApartment(apartmentId)

    companion object {
        @Volatile private var instance: ApartmentRepository? = null

        fun getInstance(apartmentDao : ApartmentDao) =
            instance ?: synchronized(this) {
                instance ?: ApartmentRepository(apartmentDao).also { instance = it }
            }
    }

    private fun refreshApartment() {
        ApartmentDBRetrofit.getService().requerstApartment().enqueue(object : Callback<List<Apartment>> {
            override fun onFailure(call: Call<List<Apartment>>, t: Throwable) {
            }

            override fun onResponse(call: Call<List<Apartment>>, response: Response<List<Apartment>>) {
                if (response.isSuccessful) {
                    GlobalScope.launch {
                        response.body()?.let { apartmentDao.insertAll(it) };
                    }
                }
            }
        })
    }
}