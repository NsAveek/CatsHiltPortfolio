package com.personal.hilt.di.modules.viewModelModule

import androidx.lifecycle.ViewModelProvider
import com.personal.hilt.api.CatsDataService
import com.personal.hilt.database.CatsDatabase
import com.personal.hilt.di.modules.core.BaseModule.provideCatsDataRepository
import com.personal.hilt.ui.main.ViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    @ViewModelScoped
    fun provideViewModelFactory(service: CatsDataService,
                                database: CatsDatabase
    ) : ViewModelProvider.Factory{
        return ViewModelFactory(provideCatsDataRepository(service,database))
    }
}