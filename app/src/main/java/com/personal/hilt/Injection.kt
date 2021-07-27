package com.personal.hilt

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.personal.hilt.api.CatsDataService
import com.personal.hilt.data.CatsDataRepository
import com.personal.hilt.database.CatsDatabase
import com.personal.hilt.ui.main.ViewModelFactory

object Injection {
    private fun provideCatsRepository(context : Context) : CatsDataRepository{
        return CatsDataRepository(CatsDataService.create(), CatsDatabase.getInstance(context))
    }

    fun provideViewModelFactory(context: Context) : ViewModelProvider.Factory{
        return ViewModelFactory(provideCatsRepository(context))
    }
}