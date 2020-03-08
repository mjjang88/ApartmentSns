package com.mjjang.apartmentsns.data

import androidx.annotation.Nullable
import androidx.room.*
import java.util.*

@Entity(
    tableName = "posts",
    foreignKeys = arrayOf(
        ForeignKey(
            entity = Apartment::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("apartment_id")
        )),
    indices = arrayOf(Index(value = ["apartment_id"]))
)
data class Post (
    val apartment_id: Int,
    @PrimaryKey val post_id: Int,
    val title: String,
    val writer: String,
    val content: String,
    val src_url: String
){
    override fun toString(): String {
        return title
    }
}