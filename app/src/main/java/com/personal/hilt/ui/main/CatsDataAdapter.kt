package com.personal.hilt.ui.main

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.personal.hilt.model.CatsDataResponseItem

class CatsDataAdapter : PagingDataAdapter<CatsDataResponseItem, CatsAdapterViewHolder>(
    REPO_COMPARATOR
) {

    override fun onBindViewHolder(holder: CatsAdapterViewHolder, position: Int) {
        val cat = getItem(position)
        if (cat!=null){
            holder.bind(cat)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatsAdapterViewHolder {
        return CatsAdapterViewHolder.create(parent)
    }

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<CatsDataResponseItem>() {
            override fun areItemsTheSame(oldItem: CatsDataResponseItem, newItem: CatsDataResponseItem): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: CatsDataResponseItem, newItem: CatsDataResponseItem): Boolean =
                oldItem == newItem
        }
    }
}