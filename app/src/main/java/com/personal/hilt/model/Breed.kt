package com.personal.hilt.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "breed")
data class Breed(
    val adaptability: Int,
    val affection_level: Int,
    val alt_names: String,
    val cfa_url: String,
    val child_friendly: Int,
    val country_code: String,
    val country_codes: String,
    val description: String,
    val dog_friendly: Int,
    val energy_level: Int,
    val experimental: Int,
    val grooming: Int,
    val hairless: Int,
    val health_issues: Int,
    val hypoallergenic: Int,
    @PrimaryKey
    @ColumnInfo(name = "breed_id") val id: String,
    @ColumnInfo(name = "cats_id_fkey") val catsId: String,
    val indoor: Int,
    val intelligence: Int,
    val life_span: String,
    val name: String,
    val natural: Int,
    val origin: String,
    val rare: Int,
    val reference_image_id: String,
    val rex: Int,
    val shedding_level: Int,
    val short_legs: Int,
    val social_needs: Int,
    val stranger_friendly: Int,
    val suppressed_tail: Int,
    val temperament: String,
    val vcahospitals_url: String,
    val vetstreet_url: String,
    val vocalisation: Int,
    val weight: Weight,
    val wikipedia_url: String
)