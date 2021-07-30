package com.personal.hilt.database

import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.personal.hilt.model.Breed
import com.personal.hilt.model.Category
import com.personal.hilt.model.CatsDataResponseItem
import java.util.concurrent.Flow

@Dao
abstract class CatsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertAll(catsDataLists: List<CatsDataResponseItem>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertAllBreeds(breedsList: List<Breed>)// or BreedDao interface


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertAllCategories(categories : List<Category>)// or CategoryDao interface

    @Query("DELETE FROM items")
    abstract suspend fun clearRepos()

    @Query("SELECT * FROM items")
    abstract fun getAllCatsDataFromDb() : PagingSource<Int,CatsDataResponseItem>
}