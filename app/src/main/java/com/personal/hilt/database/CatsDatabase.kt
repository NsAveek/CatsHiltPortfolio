package com.personal.hilt.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.personal.hilt.model.CatsDataResponseItem

@Database(entities = [CatsDataResponseItem::class, RemoteKeys::class],version = 1,exportSchema = false)

abstract class CatsDatabase : RoomDatabase() {
    abstract fun catsDao() : CatsDao
    abstract fun remoteKeysDao(): RemoteKeysDao

    companion object{
        @Volatile
        private var INSTANCE : CatsDatabase? = null

        fun getInstance(context : Context) : CatsDatabase = INSTANCE?: synchronized(this){
            INSTANCE?: buildDatabase(context).also { INSTANCE = it }
        }
        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                CatsDatabase::class.java, "cats.db")
                .fallbackToDestructiveMigration() // so that we do not need to change the db version
                .build()
    }

}