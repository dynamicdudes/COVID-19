package com.dynamicdudes.covid_19.data


import com.google.gson.annotations.SerializedName

data class CountryInfo(
    @SerializedName("country")
    val country: String,
    @SerializedName("flag")
    val flag: String,
    @SerializedName("_id")
    val id: Int,
    @SerializedName("iso2")
    val iso2: String,
    @SerializedName("iso3")
    val iso3: String,
    @SerializedName("lat")
    val lat: Int,
    @SerializedName("long")
    val long: Int
)