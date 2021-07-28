package com.personal.hilt.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class Category(
    @PrimaryKey
    @ColumnInfo(name = "category_id") val id: Int,
    val name: String,
    @ColumnInfo(name = "cats_id_fkey") val catsId: String,
)