package com.personal.hilt.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.personal.hilt.R
import com.personal.hilt.model.CatsDataResponseItem

class CatsAdapterViewHolder (view : View) : RecyclerView.ViewHolder(view){
    companion object {
        fun create(parent: ViewGroup): CatsAdapterViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_cat_single, parent, false)
            return CatsAdapterViewHolder(view)
        }
    }

    fun bind(item : CatsDataResponseItem){


    }
}