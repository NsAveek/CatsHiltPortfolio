package com.personal.hilt

import androidx.lifecycle.ViewModelProvider
import com.personal.hilt.api.CatsDataService
import com.personal.hilt.data.CatsDataRepository
import com.personal.hilt.ui.main.ViewModelFactory

object Injection {
    private fun provideCatsRepository() : CatsDataRepository{
        return CatsDataRepository(CatsDataService.create())
    }

    fun provideViewModelFactory() : ViewModelProvider.Factory{
        return ViewModelFactory(provideCatsRepository())
    }
}