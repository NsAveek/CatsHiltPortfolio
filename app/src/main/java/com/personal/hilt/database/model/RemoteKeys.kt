package com.personal.hilt.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class RemoteKeys(
    @PrimaryKey
    val catsId: String,
    val prevKey: Int?,
    val nextKey: Int?
)