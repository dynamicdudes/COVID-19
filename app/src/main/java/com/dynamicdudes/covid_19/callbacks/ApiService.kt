package com.dynamicdudes.covid_19.callbacks

import com.dynamicdudes.covid_19.data.Coviddata
import com.dynamicdudes.covid_19.data.all_countries
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("v2/countries/{country}")
    fun fetchAllDetails(@Path("country")countryName:String) : Call<Coviddata>

    @GET("v2/countries?sort=cases")
    fun fetchAllCountries():Call<all_countries>
}
