package com.dynamicdudes.covid_19

data class Covid(
    val countryFlag : String,
    val cases: Int,
    val todayCases : Int,
    val deaths : Int,
    val todayDeaths : Int,
    val recovered : Int,
    val activeCase : Int
)