package com.personal.hilt.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.personal.hilt.R
import com.personal.hilt.model.CatsDataResponseItem
import com.personal.hilt.model.UiModel

class CatsAdapterViewHolder (val view : View) : RecyclerView.ViewHolder(view){
    private val name : TextView = view.findViewById(R.id.name_txt)
    private val origin : TextView = view.findViewById(R.id.origin_txt)
    private val image : ImageView = view.findViewById(R.id.cat_image)

    companion object {
        fun create(parent: ViewGroup): CatsAdapterViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_cat_single, parent, false)
            return CatsAdapterViewHolder(view)
        }
    }

    fun bind(item : CatsDataResponseItem){
        Glide.with(view.context).load(item.url)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .into(image);

    }
}