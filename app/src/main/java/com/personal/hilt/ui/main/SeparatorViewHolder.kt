package com.personal.hilt.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.personal.hilt.R

class SeparatorViewHolder (val view : View) : RecyclerView.ViewHolder(view) {
    private val descriptor = view.findViewById<TextView>(R.id.separator_description)
    fun bind(text: String){
        descriptor.text = text
    }
    companion object{
        fun create(parent: ViewGroup) : SeparatorViewHolder{
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.separator, parent, false)
            return SeparatorViewHolder(view)
        }
    }
}