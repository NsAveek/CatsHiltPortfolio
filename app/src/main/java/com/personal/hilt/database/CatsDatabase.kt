package com.personal.hilt.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.personal.hilt.database.model.RemoteKeys
import com.personal.hilt.model.Breed
import com.personal.hilt.model.Category
import com.personal.hilt.model.CatsDataResponseItem
import com.personal.hilt.model.Weight

@Database(entities = [CatsDataResponseItem::class, RemoteKeys::class,
    Breed::class, Category::class, Weight::class],version = 1,exportSchema = false)
abstract class CatsDatabase : RoomDatabase(){
    abstract fun catsDao() : CatsDao
    abstract fun remoteKeysDao(): RemoteKeysDao
}