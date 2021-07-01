package com.personal.hilt.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.personal.hilt.R
import com.personal.hilt.databinding.CatsLoadStateFooterViewItemBinding

class CatsLoadStateViewHolder(
    private val binding: CatsLoadStateFooterViewItemBinding,
    retry: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    init {
          binding.retryButton.setOnClickListener{
              retry.invoke()}
    }
    fun bind(loadState: LoadState){
        if (loadState is LoadState.Error){
            binding.errorMsg.text = loadState.error.localizedMessage
        }
        with(binding){
            progressBar.isVisible = loadState is LoadState.Loading
            retryButton.isVisible = loadState is LoadState.Error
            errorMsg.isVisible = loadState is LoadState.Error
        }
    }
    companion object{
        fun create(parent : ViewGroup, retry: () -> Unit) : CatsLoadStateViewHolder{
            val view = LayoutInflater.from(parent.context).inflate(R.layout.cats_load_state_footer_view_item, parent, false)
            val binding = CatsLoadStateFooterViewItemBinding.bind(view)
            return CatsLoadStateViewHolder(binding, retry)
        }
    }
}