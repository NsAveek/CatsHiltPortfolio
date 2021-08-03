package com.personal.hilt.di.modules.core

import com.personal.hilt.api.CatsDataService
import com.personal.hilt.data.CatsDataRepository
import com.personal.hilt.database.CatsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module(includes = [DatabaseModule::class, ServiceModule::class])
object BaseModule {
    @Provides
    @Singleton
    fun provideCatsDataRepository(service: CatsDataService,
                                  database: CatsDatabase) : CatsDataRepository {
        return CatsDataRepository(service, database)
    }
}