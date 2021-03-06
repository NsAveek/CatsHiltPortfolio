package com.personal.hilt.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.insertSeparators
import androidx.paging.map
import com.personal.hilt.data.CatsDataRepository
import com.personal.hilt.model.UiModel
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel @Inject constructor( private val repository: CatsDataRepository) : ViewModel() {
    fun getCatsData() : Flow<PagingData<UiModel>>{
        return repository.getCatsStream().map {
            pagingData -> pagingData.map { UiModel.Response(it) }
        }.map {
            it.insertSeparators<UiModel.Response,UiModel>{
                before, after ->
                if(after == null){
                    return@insertSeparators null
                }
                if (before == null){
                    return@insertSeparators UiModel.ItemSeparator("Hi Aveek")
                }
                else{
                    null
                }
            }
        }.cachedIn(viewModelScope)
    }
}