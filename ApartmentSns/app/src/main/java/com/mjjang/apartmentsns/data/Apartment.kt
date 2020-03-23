package com.mjjang.apartmentsns.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "apartments")
data class Apartment(
    @PrimaryKey @ColumnInfo(name = "id") val id: String,
    val name: String,
    val description: String?,
    val imageUrl: String?
) {

    override fun toString(): String {
        return name
    }
}