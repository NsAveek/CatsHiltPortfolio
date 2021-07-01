package com.personal.hilt.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.personal.hilt.api.CatsDataService
import com.personal.hilt.data.CatsDataRepository.Companion.NETWORK_PAGE_SIZE
import com.personal.hilt.model.CatsDataResponseItem
import retrofit2.HttpException
import java.io.IOException

private const val CATS_STARTING_PAGE_INDEX = 1
class CatsPagingSource (private val service: CatsDataService) : PagingSource<Int, CatsDataResponseItem> () {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CatsDataResponseItem> {
        val nextPage = params.key?: CATS_STARTING_PAGE_INDEX
//        val apiQuery = query // TODO : Check IN_QUALIFIER
        return try {

            val response = service.getCats(NETWORK_PAGE_SIZE,nextPage)
            val nextKey = if (response.isEmpty()){ // Next Key will be null if there is no other data to load
                null
            }else{
//                nextPage+(params.loadSize/NETWORK_PAGE_SIZE)
                nextPage+1 // Else pass the next page number
            }
            LoadResult.Page(
                data = response as List<CatsDataResponseItem>,
                prevKey = if (nextPage == CATS_STARTING_PAGE_INDEX) null else nextPage-1,
                nextKey = nextKey
            )
        }catch (exception : IOException){
            return LoadResult.Error(exception)
        }catch (exception : HttpException){
            return LoadResult.Error(exception)
        }

    }
    override fun getRefreshKey(state: PagingState<Int, CatsDataResponseItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)?:
            state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}