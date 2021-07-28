package com.personal.hilt.model

import androidx.room.*

@Entity(tableName = "breed")
data class Breed @JvmOverloads constructor(
    @PrimaryKey
    @ColumnInfo(name = "breed_id") var id: String = "",
    @ColumnInfo(name = "cats_id_fkey") var catsId: String = "", // Foreign Key
    var adaptability: Int = 0,
    var affection_level: Int = 0,
    var alt_names: String = "",
    var cfa_url:  String = "",
    var child_friendly: Int = 0,
    var country_code:  String = "",
    var country_codes:  String = "",
    var description:  String = "",
    var dog_friendly: Int = 0,
    var energy_level: Int = 0,
    var experimental: Int = 0,
    var grooming: Int = 0,
    var hairless: Int = 0,
    var health_issues: Int = 0,
    var hypoallergenic: Int = 0,
    var indoor: Int = 0,
    var intelligence: Int = 0,
    var life_span:  String = "",
    var name:  String = "",
    var natural: Int = 0,
    var origin:  String = "",
    var rare: Int = 0,
    var reference_image_id:  String = "",
    var rex: Int = 0,
    var shedding_level: Int = 0,
    var short_legs: Int = 0,
    var social_needs: Int = 0,
    var stranger_friendly: Int = 0,
    var suppressed_tail: Int = 0,
    var temperament:  String = "",
    var vcahospitals_url:  String = "",
    var vetstreet_url:  String = "",
    var vocalisation: Int = 0,
    @Ignore val weight: Weight?,
    var wikipedia_url: String
){
    constructor():this("","",0,0,"","",0,"","",
        "",0,0,0,0,0,0,
    0,0,0,"","",0,"",0,"",0,0,
    0,0,0,0,"","","",0,
    null,"")
}