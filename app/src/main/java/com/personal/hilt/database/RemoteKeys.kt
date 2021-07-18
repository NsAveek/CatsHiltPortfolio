package com.personal.hilt.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class RemoteKeys(
    @PrimaryKey
    val catsId: Long,
    val prevKey: Int?,
    val nextKey: Int?
)