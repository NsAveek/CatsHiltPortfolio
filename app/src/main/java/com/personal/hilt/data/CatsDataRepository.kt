package com.personal.hilt.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.personal.hilt.api.CatsDataService
import com.personal.hilt.model.CatsDataResponse
import com.personal.hilt.model.CatsDataResponseItem
import kotlinx.coroutines.flow.Flow

class CatsDataRepository (private val service : CatsDataService){
    fun getCatsStream() : Flow<PagingData<CatsDataResponseItem>>{
        return Pager(config = PagingConfig(NETWORK_PAGE_SIZE,enablePlaceholders = false),
            pagingSourceFactory = {CatsPagingSource(service)}).flow
    }

    companion object {
        const val NETWORK_PAGE_SIZE = 20
    }

}