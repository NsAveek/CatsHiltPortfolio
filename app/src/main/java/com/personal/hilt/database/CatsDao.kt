package com.personal.hilt.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.personal.hilt.model.CatsDataResponseItem

@Dao
interface CatsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(repos: List<CatsDataResponseItem>)

    @Query("DELETE FROM items")
    suspend fun clearRepos()
}