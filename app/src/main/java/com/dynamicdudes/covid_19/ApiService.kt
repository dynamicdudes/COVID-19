package com.dynamicdudes.covid_19

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("/countries/india")
    fun fetchAllDetails() : Call<Covid>
}