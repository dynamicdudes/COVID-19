package com.dynamicdudes.covid_19.data

import com.google.gson.annotations.SerializedName

    data class Coviddata(
        @SerializedName("countryInfo")
        val countryInfo: CountryInfo,
        @SerializedName("iso3")
        val iso3: String,
        @SerializedName("active")
        val active: Int,
        @SerializedName("cases")
        val cases: Int,
        @SerializedName("casesPerOneMillion")
        val casesPerOneMillion: Double,
        @SerializedName("country")
        val country: String,
        @SerializedName("critical")
        val critical: Int,
        @SerializedName("deaths")
        val deaths: Int,
        @SerializedName("deathsPerOneMillion")
        val deathsPerOneMillion: Double,
        @SerializedName("recovered")
        val recovered: Int,
        @SerializedName("todayCases")
        val todayCases: Int,
        @SerializedName("todayDeaths")
        val todayDeaths: Int,
        @SerializedName("updated")
        val updated : Long
    )
