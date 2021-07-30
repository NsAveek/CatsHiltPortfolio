package com.personal.hilt.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.personal.hilt.api.CatsDataService
import com.personal.hilt.database.CatsDatabase
import com.personal.hilt.model.CatsDataResponse
import com.personal.hilt.model.CatsDataResponseItem
import kotlinx.coroutines.flow.Flow

class CatsDataRepository (private val service : CatsDataService, private val database : CatsDatabase){

    /*fun getCatsStream() : Flow<PagingData<CatsDataResponseItem>>{
        return Pager(config = PagingConfig(NETWORK_PAGE_SIZE,enablePlaceholders = false),
            pagingSourceFactory = {CatsPagingSource(service)}).flow
    }*/

    fun getCatsStream() : Flow<PagingData<CatsDataResponseItem>>{
        @OptIn(ExperimentalPagingApi::class)
        return Pager(
            config = PagingConfig(NETWORK_PAGE_SIZE,
            prefetchDistance = 5 ,
            enablePlaceholders = false,
            initialLoadSize = NETWORK_PAGE_SIZE),
            remoteMediator = CatsRemoteMediator(service,database),
            pagingSourceFactory = {database.catsDao().getAllCatsDataFromDb()}).flow
    }
    //A
    // JumpThreshold
    // MAX_BOUND

    companion object {
        const val NETWORK_PAGE_SIZE = 20
    }

}