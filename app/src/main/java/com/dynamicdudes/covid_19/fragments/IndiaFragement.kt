package com.dynamicdudes.covid_19.fragments


import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.util.Log.d
import android.view.View
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.dynamicdudes.covid_19.R
import com.dynamicdudes.covid_19.callbacks.ApiService
import com.dynamicdudes.covid_19.data.Covid
import com.dynamicdudes.covid_19.data.Coviddata
import kotlinx.android.synthetic.main.fragement_india.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class IndiaFragement : Fragment(R.layout.fragement_india){
    val TAG = "IndiaFragment"
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initApiCalls()

    }

    fun initApiCalls(){
        val retrofit = Retrofit.Builder()
            .baseUrl("https://corona.lmao.ninja")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val api = retrofit.create(ApiService::class.java)
        api.fetchAllDetails("india").enqueue(object : Callback<Coviddata> {
            override fun onFailure(call: Call<Coviddata>, t: Throwable) {
                println("Failed to Fetch...")
                Log.d(TAG, "${t.message} because of ${t.cause}")
            }
            override fun onResponse(call: Call<Coviddata>, response: Response<Coviddata>) {
                if(response.isSuccessful){
                    d(TAG, "Fetched Successfully....Cases: ${response.body()!!.cases}")
                    val flagURL = response.body()!!.countryInfo.flag
                    d(TAG,"Flag url $flagURL")
                    Glide.with(activity!!)
                        .load(Uri.parse(flagURL))
                        .into(country_flag_def)
                }
            }
        })
    }
}