package com.personal.hilt.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "items")
data class CatsDataResponseItem @JvmOverloads constructor(
    @PrimaryKey
    @ColumnInfo(name = "cats_id") var id: String = "",
    var height: Int = 0,
    var url: String = "",
    var width: Int = 0,
    @Ignore
    @field:SerializedName("breeds") val breeds: ArrayList<Breed>, // Since Room does not support auto insertion,
    @Ignore
    @field:SerializedName("categories") val categories: ArrayList<Category> // We need to insert it separately
)
{
    constructor() : this(id="", height=0, url="", width=0,breeds = arrayListOf(), categories = arrayListOf())
}