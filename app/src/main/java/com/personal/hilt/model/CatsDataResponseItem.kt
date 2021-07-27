package com.personal.hilt.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "items")
data class CatsDataResponseItem(
    @PrimaryKey
    @field:SerializedName("id") @ColumnInfo(name = "cats_id") val id: String,
//    val breeds: ArrayList<Breed>,
//    val categories: ArrayList<Category>,
    @field:SerializedName("height") val height: Int,
    @field:SerializedName("url") val url: String,
    @field:SerializedName("width") val width: Int
)