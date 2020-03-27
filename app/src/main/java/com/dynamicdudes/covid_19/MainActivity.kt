package com.dynamicdudes.covid_19

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
                .baseUrl("https://corona.lmao.ninja")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val api = retrofit.create(ApiService::class.java)
        api.fetchAllDetails().enqueue(object : Callback<Covid>{
            override fun onFailure(call: Call<Covid>, t: Throwable) {
                println("Failed to Fetch...")
            }

            override fun onResponse(call: Call<Covid>, response: Response<Covid>) {
                println("Fetched Successfully....Cases: ${response.body()!!.cases}")
            }
        })
    }
}
