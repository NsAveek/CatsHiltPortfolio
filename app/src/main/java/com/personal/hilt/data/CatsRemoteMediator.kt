package com.personal.hilt.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.personal.hilt.api.CatsDataService
import com.personal.hilt.database.CatsDatabase
import com.personal.hilt.database.RemoteKeys
import com.personal.hilt.model.CatsDataResponseItem
import retrofit2.HttpException
import java.io.IOException

private const val CATS_STARTING_PAGE_INDEX = 1

@OptIn(ExperimentalPagingApi::class)
class CatsRemoteMediator(private val service : CatsDataService,
                         private val catsDatabase : CatsDatabase) :
    RemoteMediator<Int, CatsDataResponseItem>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CatsDataResponseItem>
    ): MediatorResult {
        val page = when(loadType){
            LoadType.APPEND ->{
                // TODO
            }
            LoadType.PREPEND -> {
                // TODO
            }
            LoadType.REFRESH ->{
                // TODO
            }
        }
        try {
            val apiResponse = service.getCats(pageSize = state.config.pageSize,pageNumber = page)
            val catsDataResponseItemList = apiResponse as List<CatsDataResponseItem>
            val endOfPaginationReached = catsDataResponseItemList.isEmpty()
            catsDatabase.withTransaction{
                if (loadType == LoadType.REFRESH){
                    // Fresh Call - Clear all data
                    catsDatabase.remoteKeysDao().clearRemoteKeys()
                    catsDatabase.catsDao().clearRepos()
                }
                val prevKey = if (page == CATS_STARTING_PAGE_INDEX) null else page-1,
                val nextKey = if (endOfPaginationReached) null else page+1
                val keys = catsDataResponseItemList.map {
                    RemoteKeys(catsId = it.id.toLong(), prevKey, nextKey)
                }
                catsDatabase.remoteKeysDao().insertAll(keys)
                catsDatabase.catsDao().insertAll(catsDataResponseItemList)
            }
            return MediatorResult.Success(endOfPaginationReached)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }

}
