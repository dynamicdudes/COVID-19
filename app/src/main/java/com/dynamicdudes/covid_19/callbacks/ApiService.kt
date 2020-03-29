package com.dynamicdudes.covid_19.callbacks

import com.dynamicdudes.covid_19.data.CountryInfo
import com.dynamicdudes.covid_19.data.Covid
import com.dynamicdudes.covid_19.data.Coviddata
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/countries/{country}")
    fun fetchAllDetails(@Path("country")countryName:String) : Call<Coviddata>

    @GET("/countries/{country}")
    fun fetchCountryDetails(@Path("country")countryName: String) : Call<CountryInfo>
}