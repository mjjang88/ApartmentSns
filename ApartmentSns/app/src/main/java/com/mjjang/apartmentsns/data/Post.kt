package com.mjjang.apartmentsns.data

import androidx.room.*
import java.util.*

@Entity(
    tableName = "posts",
    foreignKeys = [
        ForeignKey(entity = Apartment::class, parentColumns = ["id"], childColumns = ["apartment_id"])
    ],
    indices = [Index("apartment_id")]
)
data class Post (
    val apartment_id: Int,
    @PrimaryKey val post_id: Int,
    val title: String,
    val writer: String,
    val create_date: Calendar = Calendar.getInstance(),
    val content: String,
    val src_url: String
){
    override fun toString(): String {
        return title
    }
}