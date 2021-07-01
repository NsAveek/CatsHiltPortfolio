package com.personal.hilt.ui.main

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter

class CatsLoadStateAdapter(private val retry : ()-> Unit) :
    LoadStateAdapter<CatsLoadStateViewHolder>() {
    override fun onBindViewHolder(holder: CatsLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): CatsLoadStateViewHolder {
        return CatsLoadStateViewHolder.create(parent, retry)
    }
}