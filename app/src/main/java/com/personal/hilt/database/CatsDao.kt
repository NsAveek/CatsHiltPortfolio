package com.personal.hilt.database

import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.personal.hilt.model.CatsDataResponseItem
import java.util.concurrent.Flow

@Dao
interface CatsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(repos: List<CatsDataResponseItem>)

    @Query("DELETE FROM items")
    suspend fun clearRepos()

    @Query("SELECT * FROM items")
    fun getAllCatsDataFromDb() : PagingSource<Int,CatsDataResponseItem>
}