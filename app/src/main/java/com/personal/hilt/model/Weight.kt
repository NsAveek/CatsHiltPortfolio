package com.personal.hilt.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weight")
data class Weight(
    @PrimaryKey
    val id : String,
    val breedId : String, // foreign key
    val imperial: String,
    val metric: String
){
    constructor():this("","","","")
}