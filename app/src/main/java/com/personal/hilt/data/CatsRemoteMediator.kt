package com.personal.hilt.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
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
                val remoteKeys = getRemoteKeyForLastItem(state)
                remoteKeys?.nextKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys!=null)
            }
            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                remoteKeys?.prevKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys!=null)
            }
            LoadType.REFRESH ->{
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: CATS_STARTING_PAGE_INDEX
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
                val prevKey = if (page == CATS_STARTING_PAGE_INDEX) null else page-1
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

    /*
    If remoteKeys is null, that means the refresh result is not in the database yet.
     We can return Success with endOfPaginationReached = false because Paging will call this method again if RemoteKeys becomes non-null.
    If remoteKeys is not null but its nextKey is null, that means we've reached the end of pagination for append.
    */


    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, CatsDataResponseItem>) : RemoteKeys?{
        return state.pages.lastOrNull(){ it.data.isNotEmpty() }?.data?.lastOrNull()?.let {
            catsItem -> catsDatabase.remoteKeysDao().remoteKeysCatsId(catsItem.id.toLong())
        }
    }
    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, CatsDataResponseItem>): RemoteKeys? {
        // Get the first page that was retrieved, that contained items.
        // From that first page, get the first item
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { catsItem ->
                // Get the remote keys of the first items retrieved
                catsDatabase.remoteKeysDao().remoteKeysCatsId(catsItem.id.toLong())
            }
    }
    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, CatsDataResponseItem>
    ): RemoteKeys? {
        // The paging library is trying to load data after the anchor position
        // Get the item closest to the anchor position
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { catsId ->
                catsDatabase.remoteKeysDao().remoteKeysCatsId(catsId.toLong())
            }
        }
    }

}
