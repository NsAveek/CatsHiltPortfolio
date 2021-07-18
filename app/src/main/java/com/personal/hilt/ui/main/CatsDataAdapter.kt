package com.personal.hilt.ui.main

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.personal.hilt.R
import com.personal.hilt.model.UiModel

class CatsDataAdapter : PagingDataAdapter<UiModel, RecyclerView.ViewHolder>(
    UI_MODEL_COMPARATOR
) {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val uiModel = getItem(position)
        uiModel.let {
            when(uiModel){
                is UiModel.Response -> (holder as CatsAdapterViewHolder).bind(uiModel.catsDataResponseItem)
                is UiModel.ItemSeparator ->(holder as SeparatorViewHolder).bind(uiModel.description)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == R.layout.layout_cat_single) {
            CatsAdapterViewHolder.create(parent)
        }
        else {
            SeparatorViewHolder.create(parent)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is UiModel.Response -> R.layout.layout_cat_single
            is UiModel.ItemSeparator -> R.layout.separator
            null -> throw UnsupportedOperationException("Unknown view")
        }
    }

    companion object {
        private val UI_MODEL_COMPARATOR = object : DiffUtil.ItemCallback<UiModel>() {
            override fun areItemsTheSame(oldItem: UiModel, newItem: UiModel): Boolean {
                return (oldItem is UiModel.Response && newItem is UiModel.Response &&
                        oldItem.catsDataResponseItem.id == newItem.catsDataResponseItem.id) ||
                        (oldItem is UiModel.ItemSeparator && newItem is UiModel.ItemSeparator
                                && oldItem.description == newItem.description)
            }

            override fun areContentsTheSame(oldItem: UiModel, newItem: UiModel): Boolean =
                oldItem == newItem
        }
    }
}