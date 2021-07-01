package com.personal.hilt.model

data class CatsDataResponseItem(
    val breeds: ArrayList<Breed>,
    val categories: ArrayList<Category>,
    val height: Int,
    val id: String,
    val url: String,
    val width: Int
)