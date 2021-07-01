package com.personal.hilt.ui.main

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.personal.hilt.data.CatsDataRepository
import com.personal.hilt.model.CatsDataResponseItem
import kotlinx.coroutines.flow.Flow

class MainFragmentViewModel ( private val repository: CatsDataRepository) : ViewModel() {
    fun getCatsData() : Flow<PagingData<CatsDataResponseItem>>{
        return repository.getCatsStream()
    }
}