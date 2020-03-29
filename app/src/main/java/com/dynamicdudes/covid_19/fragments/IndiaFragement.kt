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
        country_name_def.visibility = View.INVISIBLE
        country_flag_def.visibility = View.INVISIBLE
        update_date_def.visibility = View.INVISIBLE
        scrollview_def.visibility = View.INVISIBLE
        progress_def.visibility = View.VISIBLE
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
                d(TAG, "${t.message} because of ${t.cause}")
            }
            override fun onResponse(call: Call<Coviddata>, response: Response<Coviddata>) {
                if(response.isSuccessful){
                    d(TAG, "Fetched Successfully....Cases: ${response.body()!!.cases}")
                    val flagURL = response.body()!!.countryInfo.flag
                    val responseJson = response.body()!!
                    d(TAG,"Flag url $flagURL")
                    Glide.with(activity!!)
                        .load(Uri.parse(flagURL))
                        .into(country_flag_def)
                    country_name_def.text = responseJson.country
                    //("Substring the Update date for proper format")
                    update_date_def.text = "Last update Date : \n ${responseJson.updated}"
                    total_cases_def.text = "Total cases Count : ${responseJson.cases}"
                    today_cases_def.text = "Today's cases Count : ${responseJson.todayCases}"
                    total_death_def.text = "Total Death Count : ${responseJson.deaths}"
                    today_death_def.text = "Today Death Count : ${responseJson.todayDeaths}"
                    total_recovered_def.text = "Total Recovered Cases : ${responseJson.recovered}"
                    active_def.text = "Still Active cases : ${responseJson.active}"
                    critical_def.text = "Total Critical cases :${responseJson.critical}"
                    total_casesp_m_def.text = "Cases Per One Million : ${responseJson.casesPerOneMillion}"
                    total_deathp_m_def.text = "Death Per One Million : ${responseJson.deathsPerOneMillion}"
                    // making views visible
                    progress_def.visibility = View.INVISIBLE
                    country_name_def.visibility = View.VISIBLE
                    country_flag_def.visibility = View.VISIBLE
                    update_date_def.visibility = View.VISIBLE
                    scrollview_def.visibility = View.VISIBLE
                }
            }
        })
    }
}