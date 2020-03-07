package com.mjjang.apartmentsns.data

class ApartmentRepository private constructor(
    private val apartmentDao : ApartmentDao
) {

    fun getApartmentList() = apartmentDao.getApartmentList()
    fun getApartment(apartmentId: Int) = apartmentDao.getApartment(apartmentId)

    companion object {
        @Volatile private var instance: ApartmentRepository? = null

        fun getInstance(apartmentDao : ApartmentDao) =
            instance ?: synchronized(this) {
                instance ?: ApartmentRepository(apartmentDao).also { instance = it }
            }
    }
}