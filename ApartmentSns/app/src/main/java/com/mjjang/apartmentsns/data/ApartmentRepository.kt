package com.mjjang.apartmentsns.data

class ApartmentRepository private constructor(
    private val apartmentDao : ApartmentDao
) {

    fun getApartments() = apartmentDao.getApartment()

    companion object {
        @Volatile private var instance: ApartmentRepository? = null

        fun getInstance(apartmentDao : ApartmentDao) =
            instance ?: synchronized(this) {
                instance ?: ApartmentRepository(apartmentDao).also { instance = it }
            }
    }
}